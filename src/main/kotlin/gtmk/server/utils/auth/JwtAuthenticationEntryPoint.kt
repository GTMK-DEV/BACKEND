package gtmk.server.utils.auth

import com.fasterxml.jackson.databind.ObjectMapper
import gtmk.server.utils.base.ErrorCode
import gtmk.server.utils.base.ErrorResponse
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    @Throws(java.io.IOException::class, ServletException::class)
    override fun commence(
            request: HttpServletRequest?,
            response: HttpServletResponse?,
            authException: AuthenticationException
    ) {
        val message = authException.message

        when {
            message?.contains(ErrorCode.INVALID_JWT_TOKEN.message) == true -> {
                setErrorResponse(response!!, HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_JWT_TOKEN.message)
            }
            message?.contains(ErrorCode.EXPIRED_JWT_TOKEN.message) == true -> {
                setErrorResponse(response!!, HttpStatus.UNAUTHORIZED, ErrorCode.EXPIRED_JWT_TOKEN.message)
            }
            message?.contains(ErrorCode.UNSUPPORTED_JWT_TOKEN.message) == true -> {
                setErrorResponse(response!!, HttpStatus.UNAUTHORIZED, ErrorCode.UNSUPPORTED_JWT_TOKEN.message)
            }
            else -> {
                setErrorResponse(response!!, HttpStatus.UNAUTHORIZED, ErrorCode.ACCESS_DENIED.message)
            }
        }
    }

    @Throws(java.io.IOException::class)
    private fun setErrorResponse(
            response: HttpServletResponse,
            status: HttpStatus,
            message: String
    ) {
        response.contentType = "application/json;charset=UTF-8"
        response.status = status.value()
        val objectMapper = ObjectMapper()
        val errorResponse = ErrorResponse(400, message, status)
        val s = objectMapper.writeValueAsString(errorResponse)
        response.writer.write(s)
    }
}