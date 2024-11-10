package org.example.hmsspringboot.utils.base

import org.springframework.http.HttpStatus

enum class BaseResponseStatus(
        val isError: Boolean,
        val code: Int,
        val message: String,
        val httpStatus: HttpStatus
) {
    SUCCESS(false, 2000, "요청에 성공하였습니다.",HttpStatus.OK),
    BAD_REQUEST(true, 3027, "올바르지 않은 입력 형태 입니다.",HttpStatus.NOT_FOUND),





}
