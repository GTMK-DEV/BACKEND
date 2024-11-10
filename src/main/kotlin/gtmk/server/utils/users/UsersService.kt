package gtmk.server.utils.users

import com.fasterxml.jackson.databind.ser.Serializers.Base
import gtmk.server.utils.base.ErrorCode
import org.example.hmsspringboot.utils.base.BaseException
import org.example.hmsspringboot.utils.base.BaseResponseStatus
import org.springframework.stereotype.Service
import kotlin.math.log

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

        val newUsers = Users(req.phoneNumber, req.password, req.nickName, req.email)

        usersRepository.save(newUsers)

    }
}