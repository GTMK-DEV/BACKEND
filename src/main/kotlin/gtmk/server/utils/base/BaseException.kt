package org.example.hmsspringboot.utils.base

class BaseException : RuntimeException {

        val error: Boolean
        val code: Int

        constructor(baseResponseStatus: BaseResponseStatus) : super(baseResponseStatus.message) {
                this.error = baseResponseStatus.isError
                this.code = baseResponseStatus.code
        }

        constructor(baseResponseStatus: BaseResponseStatus, message: String) : super(message) {
                this.error = baseResponseStatus.isError
                this.code = baseResponseStatus.code
        }

        constructor(error: Boolean, message: String, code: Int) : super(message) {
                this.error = error
                this.code = code
        }

        fun get(): BaseResponse {
                return BaseResponse(error, message, code)
        }
}
