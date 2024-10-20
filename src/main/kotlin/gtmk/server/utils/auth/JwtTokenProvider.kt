//package org.example.hmsspringboot.utils.auth
//
//import io.jsonwebtoken.*
//import io.jsonwebtoken.io.Decoders
//import io.jsonwebtoken.security.Keys
//import io.jsonwebtoken.security.SignatureException
//import org.example.hmsspringboot.users.domain.entity.CustomUser
//import org.example.hmsspringboot.utils.base.BaseResponseStatus
//import org.example.hmsspringboot.utils.logger.logger
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.security.authentication.BadCredentialsException
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.stereotype.Component
//import java.util.*
//
//
//@Component
//class JwtTokenProvider {
//    @Value("\${jwt.access-secret}")
//    lateinit var accessSecretKey: String
//
//    @Value("\${jwt.refresh-secret}")
//    lateinit var refreshSecretKey: String
//
//    @Value("\${jwt.access-token-period}")
//    val accessExpireTime: Long = 0
//
//    @Value("\${jwt.refresh-token-period}")
//    val refreshExpireTime: Long = 0
//
//    private val accessKey by lazy { Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecretKey)) }
//    private val refreshKey by lazy { Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecretKey)) }
//
//    val logger = logger()
//
//    fun createToken(authentication: Authentication): JwtTokenInfoDto {
//        val authorities: String = authentication
//                .authorities
//                .joinToString(",", transform = GrantedAuthority::getAuthority)
//        val now = Date()
//        val accessExpiration = Date(now.time + accessExpireTime)
//        val refreshExpiration = Date(now.time + refreshExpireTime)
//
//        val accessToken = Jwts
//                .builder()
//                .setSubject(authentication.name)
//                .claim("auth", authorities)
//                .setIssuedAt(now)
//                .setExpiration(accessExpiration)
//                .signWith(accessKey, SignatureAlgorithm.HS256)
//                .compact()
//
//        val refreshToken = Jwts
//                .builder()
//                .setSubject(authentication.name)
//                .claim("auth", authorities)
//                .setIssuedAt(now)
//                .setExpiration(refreshExpiration)
//                .signWith(refreshKey, SignatureAlgorithm.HS256)
//                .compact()
//
//        return JwtTokenInfoDto("Bearer", accessToken, refreshToken)
//    }
//
//    fun getAuthentication(token: String): Authentication {
//        val claims: Claims = getAccessTokenClaims(token)
//        val auth = claims["auth"] ?: throw RuntimeException("잘못된 토큰입니다.")
//
//        val authorities: Collection<GrantedAuthority> = (auth as String)
//                .split(",")
//                .map { SimpleGrantedAuthority(it) }
//
//        val principal = CustomUser(claims.subject, "", authorities)
//        return UsernamePasswordAuthenticationToken(principal, "", authorities)
//    }
//
//    fun validateRefreshTokenAndCreateToken(refreshToken: String): JwtTokenInfoDto {
//        try {
//            val refreshClaims: Claims = getRefreshTokenClaims(refreshToken)
//            val now = Date()
//
//            // 새로운 access 토큰 발급
//            val newAccessToken: String = Jwts
//                    .builder()
//                    .setSubject(refreshClaims.subject)
//                    .claim("auth", refreshClaims["auth"])
//                    .setIssuedAt(now)
//                    .setExpiration(Date(now.time + accessExpireTime))
//                    .signWith(accessKey, SignatureAlgorithm.HS256)
//                    .compact()
//
//            return JwtTokenInfoDto("Bearer", newAccessToken, refreshToken)
//        } catch (e: Exception) {
//            logger.info("validateRefreshToken - $e.message")
//            throw e
//        }
//    }
//
//    fun validateAccessTokenForFilter(token: String): Boolean {
//        try {
//            getAccessTokenClaims(token)
//            return true
//        } catch (e: Exception) {
//            logger.info("validateAccessToken - $e.message")
//            when (e) {
//                is SignatureException -> throw BadCredentialsException(BaseResponseStatus.INVALID_JWT_TOKEN.message)
//                is MalformedJwtException -> throw BadCredentialsException(BaseResponseStatus.INVALID_JWT_TOKEN.message)
//                is ExpiredJwtException -> throw BadCredentialsException(BaseResponseStatus.EXPIRED_JWT_TOKEN.message)
//                is UnsupportedJwtException -> throw BadCredentialsException(BaseResponseStatus.UNSUPPORTED_JWT_TOKEN.message)
//                is IllegalArgumentException -> throw BadCredentialsException(BaseResponseStatus.INVALID_JWT_TOKEN.message)
//            }
//        }
//        return false
//    }
//
//    fun getAccessTokenClaims(token: String): Claims =
//            Jwts.parserBuilder()
//                    .setSigningKey(accessKey)
//                    .build()
//                    .parseClaimsJws(token)
//                    .body
//
//    fun getRefreshTokenClaims(token: String): Claims =
//            Jwts.parserBuilder()
//                    .setSigningKey(refreshKey)
//                    .build()
//                    .parseClaimsJws(token)
//                    .body
//}