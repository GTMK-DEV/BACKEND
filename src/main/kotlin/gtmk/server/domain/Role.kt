package gtmk.server.domain

enum class Role(val value: String) {
    ADMIN("Administrator"),
    USER("user"),
    STORE("store");

    override fun toString(): String {
        return value
    }
}