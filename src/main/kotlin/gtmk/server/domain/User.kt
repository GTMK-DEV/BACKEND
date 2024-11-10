package gtmk.server.domain

import jakarta.persistence.*
import org.example.hmsspringboot.utils.base.BaseEntity
import java.time.LocalDateTime

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    private var username: String? = null,
    private var account : String? = null,
    private var password: String? = null,
    private var phoneNumber : String? = null,
    private var lastEditedAt: LocalDateTime? = null

): BaseEntity(){
    companion object{

        fun from(id: Long) = User(id = id)

        fun of(id: Long?,
               username: String,
               account: String,
               password: String,
               phoneNumber: String,
               lastEditedAt: LocalDateTime?)
        = User(
            id = id,
            username = username,
            account = account,
            password = password,
            phoneNumber = phoneNumber,
            lastEditedAt = lastEditedAt)
    }
}