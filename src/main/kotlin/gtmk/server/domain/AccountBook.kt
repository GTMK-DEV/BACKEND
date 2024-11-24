package gtmk.server.domain

import gtmk.server.utils.base.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "account_book")
class AccountBook(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    private var bookTitle: String? = null,

    private var randomCode: String? = null,

    private var price: Long? = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private val store: Store?,

    private var lastEditedAt: LocalDateTime? = null

) : BaseEntity(){
    companion object {
        fun from(id : Long) = AccountBook(id, null, null, null, null)
        fun of(
            id: Long?,
            bookTitle: String,
            randomCode: String,
            price: Long,
            store: Store,
            lastEditedAt: LocalDateTime
        ) = AccountBook(
            id = id,
            bookTitle = bookTitle,
            randomCode = randomCode,
            price = price,
            store = store,
            lastEditedAt = lastEditedAt
        )
    }
}