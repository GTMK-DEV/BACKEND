package gtmk.server.service

import gtmk.server.api.dto.users.TokenInfoRes
import org.springframework.stereotype.Service


@Service
class UsersService(
    private val usersAuthService: UsersAuthService,
    private val storeAuthService: StoreAuthService
) {

    private val authServiceMap: Map<String, AuthService> = mapOf(
        "users" to usersAuthService,
        "store" to storeAuthService
    )

    private fun getAuthService(type: String): AuthService {
        return authServiceMap[type] ?: throw IllegalArgumentException("Invalid auth service type: $type")
    }

    fun signUp(type: String, req: Any) {
        val authService = getAuthService(type)
        authService.signUp(req)
    }

    fun signIn(type: String, req: Any): TokenInfoRes {
        val authService = getAuthService(type)
        return authService.signIn(req)
    }
}
