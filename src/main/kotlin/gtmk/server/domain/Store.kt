package gtmk.server.domain

import jakarta.persistence.*
import org.example.hmsspringboot.utils.base.BaseEntity
import java.time.LocalDateTime

@Entity
@Table(name = "store")
class Store(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    private var storeName: String? = null,
    private var phoneNumber: String? = null,
    private var account: String? = null,
    private var password: String? = null,
    private var location: String? = null,
    private var lastEditedAt: LocalDateTime? = null
) : BaseEntity() {

    companion object {
        fun from(id: Long) = Store(id = id)

        fun of(
            id: Long?,
            storeName: String,
            phoneNumber: String,
            account: String,
            password: String,
            location: String,
            lastEditedAt: LocalDateTime
        ) = Store(
            id = id,
            storeName = storeName,
            phoneNumber = phoneNumber,
            account = account,
            password = password,
            location = location,
            lastEditedAt = lastEditedAt
        )
    }

    fun updateLastEditedAt() {
        this.lastEditedAt = LocalDateTime.now()
    }
}

