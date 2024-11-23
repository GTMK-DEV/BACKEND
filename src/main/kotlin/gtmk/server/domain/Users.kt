package gtmk.server.domain

import jakarta.persistence.*
import org.example.hmsspringboot.utils.base.BaseEntity

@Entity
@Table(name = "users")

data class Users(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var phoneNumber: String? = null,

    var password: String? = null,

    var nickName: String? = null,

) : BaseEntity(){
    constructor(
        phoneNumber: String? = null,
        password: String? = null,
        nickName: String? = null,
    ) : this(
        id = null,
        phoneNumber = phoneNumber,
        password = password,
        nickName = nickName,
    )

    companion object{

        fun from(id: Long) = Users(id = id)

        fun of(
//            id: Long?,
                password: String,
               phoneNumber: String,
            nickName: String?
        ) = Users(
//            id = id,
            phoneNumber = phoneNumber,
            password = password,
            nickName = nickName,
        )

    }



}
