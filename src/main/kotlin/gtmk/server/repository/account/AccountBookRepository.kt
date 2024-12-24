package gtmk.server.repository.account

import gtmk.server.domain.AccountBook

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountBookRepository : JpaRepository<AccountBook, Long> {


}
