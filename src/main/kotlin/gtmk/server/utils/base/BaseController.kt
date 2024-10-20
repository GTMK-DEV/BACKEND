package org.example.hmsspringboot.utils.base

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import io.jsonwebtoken.JwtException
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException
import org.example.hmsspringboot.utils.base.BaseResponseStatus.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.multipart.MultipartException

@RestControllerAdvice
class BaseController {

    @Value("\${spring.servlet.multipart.max-file-size}")
    lateinit var limitSize: String

    @ExceptionHandler(BaseException::class)
    protected fun handleCustomException(ex: BaseException): ResponseEntity<BaseResponse> {
        return ResponseEntity(BaseResponse(ex), HttpStatus.OK)
    }

    @ExceptionHandler(JwtException::class)
    protected fun handleCustomException(ex: JwtException): ResponseEntity<BaseResponse> {
        val message = ex.message
        val baseResponse = when (message) {
            INVALID_JWT_TOKEN.message -> setErrorResponse(INVALID_JWT_TOKEN)
            EXPIRED_JWT_TOKEN.message -> setErrorResponse(EXPIRED_JWT_TOKEN)
            UNSUPPORTED_JWT_TOKEN.message -> setErrorResponse(UNSUPPORTED_JWT_TOKEN)
            else -> setErrorResponse(ACCESS_DENIED)
        }
        return ResponseEntity(baseResponse, HttpStatus.UNAUTHORIZED)
    }

    private fun setErrorResponse(status: BaseResponseStatus): BaseResponse {
        return BaseResponse(status.isError, status.message, status.code)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    protected fun jsonErrorCustomException(e: HttpMessageNotReadableException): ResponseEntity<BaseResponse> {
        val cause = e.cause
        if (cause is MismatchedInputException) {
            val path = cause.path
            if (path != null && path.isNotEmpty()) {
                val msg = String.format(path[0].fieldName, INVALID_FORMAT)
                return ResponseEntity(BaseException(INVALID_FORMAT, msg).get(), HttpStatus.OK)
            }
        }
        return ResponseEntity(BaseException(BAD_JSON_FORMAT).get(), HttpStatus.OK)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<BaseResponse> {
        val errors = e.bindingResult.allErrors
        val errorFields = errors
                .filterIsInstance<FieldError>()
                .map { it.field }

        val errorMessage = errorFields.toString()
        return ResponseEntity(BaseException(BAD_REQUEST, errorMessage).get(), HttpStatus.OK)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException): ResponseEntity<BaseResponse> {
        val errorMessage = e.name
        return ResponseEntity(BaseException(INVALID_DATE_TIME_FORM, errorMessage).get(), HttpStatus.OK)
    }

}
