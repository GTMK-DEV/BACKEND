//package org.example.hmsspringboot.utils.auth
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import jakarta.servlet.ServletException
//import jakarta.servlet.http.HttpServletRequest
//import jakarta.servlet.http.HttpServletResponse
//import org.example.hmsspringboot.utils.base.BaseResponse
//import org.example.hmsspringboot.utils.base.BaseResponseStatus
//import org.example.hmsspringboot.utils.logger.logger
//import org.springframework.http.HttpStatus
//import org.springframework.security.core.AuthenticationException
//import org.springframework.security.web.AuthenticationEntryPoint
//import org.springframework.stereotype.Component
//
//@Component
//class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
//    val logger = logger()
//
//    @Throws(java.io.IOException::class, ServletException::class)
//    override fun commence(
//            request: HttpServletRequest?,
//            response: HttpServletResponse?,
//            authException: AuthenticationException
//    ) {
//        val message = authException.message
//        logger.info("entrypoint - authException: $message")
//
//        when {
//            message?.contains(BaseResponseStatus.INVALID_JWT_TOKEN.message) == true -> {
//                setErrorResponse(response!!, HttpStatus.UNAUTHORIZED, BaseResponseStatus.INVALID_JWT_TOKEN)
//            }
//            message?.contains(BaseResponseStatus.EXPIRED_JWT_TOKEN.message) == true -> {
//                setErrorResponse(response!!, HttpStatus.UNAUTHORIZED, BaseResponseStatus.EXPIRED_JWT_TOKEN)
//            }
//            message?.contains(BaseResponseStatus.UNSUPPORTED_JWT_TOKEN.message) == true -> {
//                setErrorResponse(response!!, HttpStatus.UNAUTHORIZED, BaseResponseStatus.UNSUPPORTED_JWT_TOKEN)
//            }
//            else -> {
//                setErrorResponse(response!!, HttpStatus.UNAUTHORIZED, BaseResponseStatus.ACCESS_DENIED)
//            }
//        }
//    }
//
//    @Throws(java.io.IOException::class)
//    private fun setErrorResponse(
//            response: HttpServletResponse,
//            errorCode: HttpStatus,
//            status: BaseResponseStatus
//    ) {
//        response.contentType = "application/json;charset=UTF-8"
//        response.status = errorCode.value()
//        val objectMapper = ObjectMapper()
//        val errorResponse = BaseResponse(status.isError, status.message, status.code)
//        val s = objectMapper.writeValueAsString(errorResponse)
//        response.writer.write(s)
//    }
//}