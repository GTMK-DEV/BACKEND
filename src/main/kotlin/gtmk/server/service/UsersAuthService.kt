package gtmk.server.service

import gtmk.server.api.dto.users.TokenInfoRes
import gtmk.server.api.dto.users.UsersSignInReq
import gtmk.server.api.dto.users.UsersSignUpReq
import gtmk.server.domain.Users
import gtmk.server.repository.users.UsersRepository
import gtmk.server.utils.auth.JwtTokenProvider
import gtmk.server.utils.base.BaseException
import gtmk.server.utils.base.ErrorCode
import gtmk.server.utils.redis.RedisService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsersAuthService(
    private val usersRepository: UsersRepository,
    val bCryptPasswordEncoder: BCryptPasswordEncoder,
    jwtTokenProvider: JwtTokenProvider,
    redisService: RedisService,
    val authenticationManagerBuilder: AuthenticationManagerBuilder,
    @Value("\${jwt.refresh-token-period}")
    refreshExpireTime: Long

) : AbstractAuthService(bCryptPasswordEncoder, jwtTokenProvider, redisService, authenticationManagerBuilder, refreshExpireTime) {

    override fun signUp(req: Any) {
        if (req !is UsersSignUpReq) throw IllegalArgumentException("Invalid request type for Users")
        val encodePassword = encodePassword(req.password)
        val newUser = Users.of(req.phoneNumber, encodePassword, req.nickName, req.email, req.role)
        usersRepository.save(newUser)
    }

    override fun signIn(req: Any): TokenInfoRes {
        if (req !is UsersSignInReq) throw IllegalArgumentException("Invalid request type for Users")
        val loginUser = usersRepository.findByPhoneNumber(req.id) ?: throw BaseException(ErrorCode.NOT_FOUND_USERS)

        if (!bCryptPasswordEncoder.matches(req.password, loginUser.password)) {
            throw BaseException(ErrorCode.BAD_ID_AND_PASSWORD)
        }

        val authenticationToken = UsernamePasswordAuthenticationToken(req.id, req.password)
        return createAndSaveToken(loginUser.id.toString(), authenticationToken)
    }
}
