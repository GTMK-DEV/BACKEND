package gtmk.server.service

import UtilMethod
import gtmk.server.api.dto.users.AccountCreateReq
import gtmk.server.api.dto.users.AccountCreateRes
import gtmk.server.domain.AccountBook
import gtmk.server.domain.Store
import gtmk.server.repository.account.AccountBookRepository
import org.springframework.stereotype.Service

@Service
class AccountBookService(
    val accountBookRepository: AccountBookRepository
) {

    fun createAccountBook(store: Store, req: AccountCreateReq): AccountCreateRes {

        // TODO : 랜덤 코드 생성
        val randomCode = UtilMethod.generateShortUUID()
        val accountBook = AccountBook.of(req.bookTitle,randomCode,req.price,store)

        // TODO : 랜덤 코드 공유 로직 : 랜덤코드 res 에 넣으면 프론트 링크
        val savedAccount = accountBookRepository.save(accountBook)
        return AccountCreateRes(savedAccount.randomCode,savedAccount.price)


    }

}