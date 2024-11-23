package gtmk.server.utils.base

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import io.jsonwebtoken.JwtException
import org.example.hmsspringboot.utils.base.BaseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException


@RestControllerAdvice
class BaseController(

) {

    @ExceptionHandler(BaseException::class)
    fun handleApplicationException(ex: BaseException): ResponseEntity<ErrorResponse> {
        return handleException(ex, ex.errorCode,ex.status)
    }

    private fun handleException(
        ex: Exception,
        errorCode: ErrorCode,
        status: HttpStatus
    ): ResponseEntity<ErrorResponse> {

        println("${ex.javaClass.simpleName}, ${ex.message},")

        val errorResponse = ErrorResponse(errorCode, errorCode.message, status)

        return ResponseEntity(errorResponse,status)

    }


    @ExceptionHandler(JwtException::class)
    protected fun handleCustomException(ex: JwtException): ResponseEntity<ErrorResponse> {

        val message = ex.message
        val baseResponse = when (message) {
            ErrorCode.INVALID_JWT_TOKEN.message -> setErrorResponse(ErrorCode.INVALID_JWT_TOKEN)
            ErrorCode.EXPIRED_JWT_TOKEN.message -> setErrorResponse(ErrorCode.EXPIRED_JWT_TOKEN)
            ErrorCode.UNSUPPORTED_JWT_TOKEN.message -> setErrorResponse(ErrorCode.UNSUPPORTED_JWT_TOKEN)
            else -> setErrorResponse(ErrorCode.ACCESS_DENIED)
        }
        return ResponseEntity(baseResponse, HttpStatus.UNAUTHORIZED)
    }

    private fun setErrorResponse(errorCode: ErrorCode): ErrorResponse {
        return ErrorResponse(errorCode)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    protected fun jsonErrorCustomException(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        val cause = e.cause

        if (cause is MismatchedInputException) {
            val path = cause.path
            if (path != null && path.isNotEmpty()) {
                val msg = String.format(path[0].fieldName, ErrorCode.INVALID_FORMAT)
                return ResponseEntity(setErrorResponse(ErrorCode.INVALID_FORMAT),HttpStatus.BAD_REQUEST)
            }
        }
        return ResponseEntity(setErrorResponse(ErrorCode.BAD_JSON_FORMAT),HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {

        val errors = e.bindingResult.allErrors
        val errorFields = errors
                .filterIsInstance<FieldError>()
                .map { it.field }

        val errorMessage = errorFields.toString()
        return ResponseEntity(setErrorResponse(ErrorCode.INVALID_FORMAT),HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException): ResponseEntity<ErrorResponse> {

        val errorMessage = e.name
        return ResponseEntity(setErrorResponse(ErrorCode.INVALID_DATE_TIME_FORM),HttpStatus.BAD_REQUEST)
    }

}
