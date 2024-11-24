package gtmk.server.service

import gtmk.server.api.dto.users.TokenInfoRes
import gtmk.server.api.dto.users.StoreSignInReq
import gtmk.server.api.dto.users.StoreSignUpReq
import gtmk.server.domain.Store
import gtmk.server.repository.store.StoreRepository
import gtmk.server.utils.auth.JwtTokenProvider
import gtmk.server.utils.base.BaseException
import gtmk.server.utils.base.ErrorCode
import gtmk.server.utils.redis.RedisService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class StoreAuthService(
    val storeRepository: StoreRepository,
    val bCryptPasswordEncoder: BCryptPasswordEncoder,
    jwtTokenProvider: JwtTokenProvider,
    redisService: RedisService,
    authenticationManagerBuilder: AuthenticationManagerBuilder,

    @Value("\${jwt.refresh-token-period}")
    refreshExpireTime: Long
) : AbstractAuthService(bCryptPasswordEncoder, jwtTokenProvider, redisService, authenticationManagerBuilder,refreshExpireTime) {

    override fun signUp(req: Any) {
        if (req !is StoreSignUpReq) throw BaseException(ErrorCode.INVALID_FORMAT)
        val encodePassword = encodePassword(req.password)
        val newStore = Store.of(req.storeName, req.phoneNumber, req.account, encodePassword, req.location, LocalDateTime.now())
        storeRepository.save(newStore)
    }

    override fun signIn(req: Any): TokenInfoRes {
        if (req !is StoreSignInReq) throw BaseException(ErrorCode.INVALID_FORMAT)
        val loginStore = storeRepository.findByPhoneNumber(req.id) ?: throw BaseException(ErrorCode.NOT_FOUND_STORE)

        if (!bCryptPasswordEncoder.matches(req.password, loginStore.password)) {
            throw BaseException(ErrorCode.BAD_ID_AND_PASSWORD)
        }

        val authenticationToken = UsernamePasswordAuthenticationToken(req.id, req.password)
        return createAndSaveToken(loginStore.id.toString(), authenticationToken)
    }
}
