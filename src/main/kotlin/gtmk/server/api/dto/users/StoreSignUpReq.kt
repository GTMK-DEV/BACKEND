package gtmk.server.api.dto.users

import gtmk.server.domain.Role


data class StoreSignUpReq (
    val phoneNumber: String,
    val password: String,
    val account: String,
    val storeName: String,
    val location: String,
//    val role: Role
)