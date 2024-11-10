package gtmk.server.domain

import jakarta.persistence.*
import org.example.hmsspringboot.utils.base.BaseEntity

@Entity
@Table(name = "books_history")
class BooksHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_book_id", nullable = false)
    private var book: AccountBook? = null,

    private val balance: Long? = null,

    private val spendAmount: Long? = null,

    @Enumerated(EnumType.STRING)
    private val status: Status? = null,
) :BaseEntity(){
    companion object {
        fun of(id: Long?,book: AccountBook, balance: Long?, spendAmount: Long?, status: Status): BooksHistory {
            return BooksHistory(
                id = id,
                book = book,
                balance = balance,
                spendAmount = spendAmount,
                status = status
            )
        }
    }
}
