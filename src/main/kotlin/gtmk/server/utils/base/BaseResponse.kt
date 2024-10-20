package org.example.hmsspringboot.utils.base;

import lombok.AllArgsConstructor;
import lombok.Getter;



@Getter
@AllArgsConstructor
class BaseResponse(
    var error: Boolean,
    var message: String?,
    var code: Int
) {
    // 기본 요청에 성공한 경우
    constructor(result: BaseException) : this(
        error = result.error,
        message = result.message,
        code = result.code
    )

    constructor(message: String): this(
        error = BaseResponseStatus.SUCCESS.isError,
        message = message,
        code = BaseResponseStatus.SUCCESS.code

    )
    constructor(status: BaseResponseStatus): this(
        error = status.isError,
        message = status.message,
        code = status.code

    )


}

