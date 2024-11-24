package gtmk.server.utils.auth

import gtmk.server.domain.Users
import gtmk.server.repository.users.UsersRepository
import gtmk.server.utils.base.BaseException
import gtmk.server.utils.base.ErrorCode



import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val usersRepository: UsersRepository
): UserDetailsService {

    override fun loadUserByUsername(userId: String): UserDetails {
        val users = usersRepository.findByPhoneNumber(userId) ?: throw BaseException(ErrorCode.NOT_FOUND_USERS)
//            .orElseThrow { BaseException(ErrorCode.NOT_FOUND_USERS) }
        return createUserDetails(users)
    }

    private fun createUserDetails(user: Users): UserDetails =
        CustomUsers(
            user.phoneNumber,
            user.password,
            listOf(SimpleGrantedAuthority("ROLE_USER"))
        )
}