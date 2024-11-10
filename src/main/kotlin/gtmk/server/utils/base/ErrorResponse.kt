package gtmk.server.utils.base

import org.springframework.http.HttpStatus
import java.time.LocalDateTime
//
//data class ErrorResponse {
//    var isSuccess: Boolean
//    var timeStamp: LocalDateTime
//    var errorCode: Int
//    var message: String
//
//    constructor(errorCode: Int, message: String, status: HttpStatus) {
//        this.isSuccess = false
//        this.timeStamp = LocalDateTime.now().withNano(0)
//        this.errorCode = errorCode
//        this.message = message
//    }
//
//    constructor(errorCode: ErrorCode, message: String, status: HttpStatus) {
//        this.isSuccess = false
//        this.timeStamp = LocalDateTime.now().withNano(0)
//        this.errorCode = errorCode.status
//        this.message = message
//    }
//
//    constructor(errorCode: ErrorCode) {
//        this.isSuccess = false
//        this.timeStamp = LocalDateTime.now().withNano(0)
//        this.errorCode = errorCode.status
//        this.message = errorCode.message
//    }
//}

data class ErrorResponse(
    var isSuccess: Boolean = false,
    var timeStamp: LocalDateTime = LocalDateTime.now().withNano(0),
    var errorCode: Int,
    var message: String
) {
    constructor(errorCode: Int, message: String, status: HttpStatus) : this(
        isSuccess = false,
        timeStamp = LocalDateTime.now().withNano(0),
        errorCode = errorCode,
        message = message
    )

    constructor(errorCode: ErrorCode, message: String, status: HttpStatus) : this(
        isSuccess = false,
        timeStamp = LocalDateTime.now().withNano(0),
        errorCode = errorCode.status,
        message = message
    )

    constructor(errorCode: ErrorCode) : this(
        isSuccess = false,
        timeStamp = LocalDateTime.now().withNano(0),
        errorCode = errorCode.status,
        message = errorCode.message
    )
}
