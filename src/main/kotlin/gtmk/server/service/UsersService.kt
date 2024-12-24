package gtmk.server.service

import gtmk.server.api.dto.users.TokenInfoRes
import gtmk.server.domain.UserType
import org.springframework.stereotype.Service


@Service
class UsersService(
    private val usersAuthService: UsersAuthService,
    private val storeAuthService: StoreAuthService
) {

    private val authServiceMap: Map<UserType, AuthService> = mapOf(
        UserType.USER to usersAuthService,
        UserType.STORE to storeAuthService
    )

    private fun getAuthService(type: UserType): AuthService {
        return authServiceMap[type] ?: throw IllegalArgumentException("Invalid auth service type: $type")
    }

    fun signUp(type: UserType, req: Any) {
        val authService = getAuthService(type)
        authService.signUp(req)
    }

    fun signIn(type: UserType, req: Any): TokenInfoRes {
        val authService = getAuthService(type)
        return authService.signIn(req)
    }
}
