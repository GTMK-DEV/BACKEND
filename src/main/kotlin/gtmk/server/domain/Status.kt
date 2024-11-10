package gtmk.server.domain

enum class Status(
        private val value: String
) {
        PENDING("대기"),
        APPROVED("승인"),
        REJECTED("거절");

        companion object {
                fun getEnumCategoryCodeFromStringCategoryCode(stringCategoryCode: String): Status {
                        return values().firstOrNull { it.value == stringCategoryCode }
                                ?: throw IllegalArgumentException("Invalid category code: $stringCategoryCode")
                }
        }
}