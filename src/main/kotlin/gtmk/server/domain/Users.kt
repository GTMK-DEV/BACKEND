package gtmk.server.domain

import gtmk.server.utils.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class Users(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var phoneNumber: String = "",

    var password: String = "",

    var nickName: String = "",

    var email: String = "",

    var role: Role

) : BaseEntity() {

    companion object {

        fun from(id: Long, phoneNumber: String, password: String, nickName: String, role: Role) = Users(
            id = id,
            phoneNumber = phoneNumber,
            password = password,
            nickName = nickName,
            role = role
        )

        fun of(phoneNumber: String, password: String, nickName: String, email: String,role: Role ) = Users(
            phoneNumber = phoneNumber,
            password = password,
            nickName = nickName,
            email = email,
            role = role
        )
    }
}
