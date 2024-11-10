package org.example.hmsspringboot.utils.base

import gtmk.server.utils.base.ErrorCode
import org.springframework.http.HttpStatus

class BaseException : RuntimeException {

        val errorCode: ErrorCode
        var status: HttpStatus

        constructor(errorCode: ErrorCode) : super(errorCode.message) {
                this.errorCode = errorCode
                this.status = HttpStatus.valueOf(errorCode.status)
        }

        constructor(baseResponseStatus: BaseResponseStatus, message: String, errorCode: ErrorCode) : super(message) {
                this.status=baseResponseStatus.httpStatus

                this.errorCode = errorCode
        }

        constructor(error: Boolean, message: String, code: Int, errorCode: ErrorCode) : super(message) {
                this.status=HttpStatus.valueOf(code)
                this.errorCode = errorCode
        }

}
