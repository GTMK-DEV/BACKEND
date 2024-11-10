package gtmk.server.utils.users

data class UsersSignUpReq (
    val phoneNumber: String,
    val password: String,
    val nickName: String,
    val email: String,
)