package org.example.hmsspringboot.utils.base

enum class BaseResponseStatus(
        val isError: Boolean,
        val code: Int,
        val message: String
) {
    SUCCESS(false, 2000, "요청에 성공하였습니다."),
      BAD_REQUEST(true, 3027, "올바르지 않은 입력 형태 입니다."),
    BAD_JSON_FORMAT(true, -2, "잘못된 Json형식입니다."),

    INVALID_DATE_TIME_FORM(true, -2, "날짜/시간 형식이 올바르지 않습니다 : yyyy-mm-dd, yyyy-mm-dd hh:mm:ss, hh:mm:ss"),

    NO_AUTHENTICATION(true, 401, "권한이 없습니다."),
    INVALID_JWT_TOKEN(true, 401, "잘못된 토큰입니다."),
    EXPIRED_JWT_TOKEN(true, 401, "만료된 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN(true, 401, "지원하지 않는 토큰입니다."),
    INVALID_REFRESH_TOKEN(true,401,"유효하지 않는 리프레쉬 토큰입니다"),
    ACCESS_DENIED(true,401,"접근이 거부되었습니다."),
    INVALID_FORMAT(true, -2, "%s 의 형식이 잘못 되었습니다"),


}
