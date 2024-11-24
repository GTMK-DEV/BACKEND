package gtmk.server.service

import gtmk.server.api.dto.users.TokenInfoRes
import gtmk.server.utils.auth.JwtTokenProvider
import gtmk.server.utils.redis.RedisService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

abstract class AbstractAuthService(
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val redisService: RedisService,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    @Value("\${jwt.refresh-token-period}")
    private val refreshExpireTime: Long
) : AuthService {

    protected fun encodePassword(password: String): String {
        return bCryptPasswordEncoder.encode(password)
    }

    protected fun createAndSaveToken(id: String, authenticationToken: UsernamePasswordAuthenticationToken): TokenInfoRes {
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        val createToken = jwtTokenProvider.createToken(authentication)
        redisService.saveStringData(id, createToken.refreshToken, refreshExpireTime)
        return createToken
    }
}
