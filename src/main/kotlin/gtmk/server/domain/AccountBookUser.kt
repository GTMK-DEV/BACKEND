package gtmk.server.domain

import jakarta.persistence.*
import org.example.hmsspringboot.utils.base.BaseEntity

@Entity
@Table(name = "account_book_user")
class AccountBookUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private val user: Users,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private val accountBook: AccountBook,
) :BaseEntity(){
    companion object {
        fun of(id: Long?, user: Users, accountBook: AccountBook) = AccountBookUser(
            id = id,
            user = user,
            accountBook = accountBook
        )
    }
}