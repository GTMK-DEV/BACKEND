package gtmk.server.utils.users

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Users(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var phoneNumber: String? = null,

    var password: String? = null,

    var nickName: String? = null,

    var email: String? = null

) {
    constructor(
        phoneNumber: String? = null,
        password: String? = null,
        nickName: String? = null,
        email: String? = null
    ) : this(
        id = null,
        phoneNumber = phoneNumber,
        password = password,
        nickName = nickName,
        email = email
    )
}
