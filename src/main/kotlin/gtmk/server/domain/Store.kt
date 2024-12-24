package gtmk.server.domain

import gtmk.server.utils.base.BaseEntity
import jakarta.persistence.*
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "store")
data class Store(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     val id: Long? = null,
     var storeName: String? = null,
     var phoneNumber: String? = null,
     var account: String? = null,
     var password: String? = null,
     var location: String? = null,
    @LastModifiedDate
    var lastEditedAt: LocalDateTime? = null

) : BaseEntity() {

    companion object {
        fun from(id: Long) = Store(id = id)

        fun of(
//            id: Long?,
            storeName: String,
            phoneNumber: String,
            account: String,
            password: String,
            location: String,
//            lastEditedAt: LocalDateTime
        ) = Store(
//            id = null,
            storeName = storeName,
            phoneNumber = phoneNumber,
            account = account,
            password = password,
            location = location,
//            lastEditedAt = lastEditedAt
        )
    }

}

