package gtmk.server.utils.base

import lombok.AllArgsConstructor;
import lombok.Getter;



@Getter
@AllArgsConstructor
class BaseResponse(
    var message: String?,
    var code: Int
) {


    constructor(message: String): this(
        message = message,
        code = BaseResponseStatus.SUCCESS.code

    )
    constructor(status: BaseResponseStatus): this(
        message = status.message,
        code = status.code

    )


}

