package gtmk.server.utils.base

import org.springframework.http.HttpStatus


data class ErrorResponse(
    var isSuccess: Boolean = false,
    var errorCode: Int,
    var message: String
) {
    constructor(errorCode: Int, message: String, status: HttpStatus) : this(
        isSuccess = false,
        errorCode = errorCode,
        message = message
    )

    constructor(errorCode: ErrorCode, message: String, status: HttpStatus) : this(
        isSuccess = false,
        errorCode = errorCode.status,
        message = message
    )

    constructor(errorCode: ErrorCode) : this(
        isSuccess = false,
        errorCode = errorCode.status,
        message = errorCode.message
    )
}
