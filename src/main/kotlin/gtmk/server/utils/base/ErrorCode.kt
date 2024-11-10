package gtmk.server.utils.base

import org.springframework.http.HttpStatus

enum class ErrorCode (
    val status: Int,
    val message: String
) {
    BAD_REQUEST(400, "입력값이 유효하지 않습니다."),
    METHOD_NOT_ALLOWED(405, "클라이언트가 사용한 HTTP 메서드가 리소스에서 허용되지 않습니다."),
    NO_AUTHENTICATION( 401, "권한이 없습니다.", ),
    INVALID_JWT_TOKEN( 401, "잘못된 토큰입니다.", ),
    EXPIRED_JWT_TOKEN( 401, "만료된 토큰입니다.",),
    UNSUPPORTED_JWT_TOKEN( 401, "지원하지 않는 토큰입니다.",),
    ACCESS_DENIED(401,"접근이 거부되었습니다.",),
    INVALID_FORMAT( 404, "%s 의 형식이 잘못 되었습니다", ),
    BAD_JSON_FORMAT(404, "잘못된 Json형식입니다."),
    INVALID_DATE_TIME_FORM(404, "날짜/시간 형식이 올바르지 않습니다 : yyyy-mm-dd, yyyy-mm-dd hh:mm:ss, hh:mm:ss"),

    BAD_ID_AND_PASSWORD(404, "아이디 비밀번호가 일치하지 않습니다."),


}