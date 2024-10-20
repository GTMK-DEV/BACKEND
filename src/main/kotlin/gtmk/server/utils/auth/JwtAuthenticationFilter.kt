//package org.example.hmsspringboot.utils.auth
//
//import jakarta.servlet.FilterChain
//import jakarta.servlet.http.HttpServletRequest
//import jakarta.servlet.http.HttpServletResponse
//import org.example.hmsspringboot.utils.base.BaseResponseStatus
//import org.example.hmsspringboot.utils.logger.logger
//import org.springframework.security.authentication.BadCredentialsException
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.security.web.AuthenticationEntryPoint
//import org.springframework.util.StringUtils
//import org.springframework.web.filter.OncePerRequestFilter
//
//class JwtAuthenticationFilter(
//        private val jwtTokenProvider: JwtTokenProvider
//) : OncePerRequestFilter() {
//
//    override fun doFilterInternal(
//            request: HttpServletRequest,
//            response: HttpServletResponse,
//            chain: FilterChain
//    ) {
//        val header = request.getHeader("Authorization")
//
//        if(StringUtils.hasText(header)) {
//            try {
//                val token = resolveToken(request)
//                if (jwtTokenProvider.validateAccessTokenForFilter(token)) {
//                    val authentication = jwtTokenProvider.getAuthentication(token)
//                    SecurityContextHolder.getContext().authentication = authentication
//                }
//            } catch (e: BadCredentialsException) {
//                val authEntryPoint: AuthenticationEntryPoint = JwtAuthenticationEntryPoint()
//                authEntryPoint.commence(request, response, e)
//                return
//            }
//        }
//
//        chain.doFilter(request, response)
//    }
//
//    private fun resolveToken(request: HttpServletRequest): String {
//        val logger = logger()
//        val bearerToken = request.getHeader("Authorization")
//
//        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
//            bearerToken.substring(7)
//        } else {
//            logger.info("header authorization: $bearerToken")
//            throw BadCredentialsException(BaseResponseStatus.INVALID_JWT_TOKEN.message)
//        }
//    }
//}