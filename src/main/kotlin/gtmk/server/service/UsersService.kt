package gtmk.server.service

import gtmk.server.domain.Users
import gtmk.server.repository.users.UsersRepository
import gtmk.server.utils.base.ErrorCode
import gtmk.server.api.dto.users.UsersSignInReq
import gtmk.server.api.dto.users.UsersSignUpReq
import org.example.hmsspringboot.utils.base.BaseException
import org.springframework.stereotype.Service

@Service
class UsersService(
    val usersRepository: UsersRepository,
) {

    /**
     * 로그인
     *
     */
    fun signIn(req: UsersSignInReq) {

        if (usersRepository.existsByPhoneNumber(req.id)) {

            val loginUsers = usersRepository.findByPhoneNumber(req.id)

            if (loginUsers?.phoneNumber==(req.password)) {
                throw BaseException(ErrorCode.BAD_ID_AND_PASSWORD)
            }

        } else {
            throw BaseException(ErrorCode.BAD_ID_AND_PASSWORD)
        }

    }

    /**
     * 회원 가입
     *
     */
    fun signUp(req: UsersSignUpReq) {

        val newUsers = Users(req.phoneNumber, req.password, req.nickName)

        usersRepository.save(newUsers)

    }
}