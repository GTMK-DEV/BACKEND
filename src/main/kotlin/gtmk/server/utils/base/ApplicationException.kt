package gtmk.server.utils.base//package gtmk.server.utils.base
//
//import org.springframework.http.HttpStatus
//
//
//abstract class ApplicationException protected constructor(
//    errorCode: ErrorCode,
//    val httpStatus: HttpStatus
//) : RuntimeException(errorCode.message) {
//
//
//    private val errorCode: ErrorCode = errorCode
//
//    fun getErrorCode(): ErrorCode {
//        return errorCode
//    }
//}