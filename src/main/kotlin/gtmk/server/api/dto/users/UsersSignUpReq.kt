package gtmk.server.api.dto.users

import gtmk.server.domain.Role


data class UsersSignUpReq (
    val phoneNumber: String,
    val password: String,
    val nickName: String,
    val email: String,
    val role: Role
)