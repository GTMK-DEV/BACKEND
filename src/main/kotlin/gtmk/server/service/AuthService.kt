package gtmk.server.service

import gtmk.server.api.dto.users.TokenInfoRes

interface AuthService {
    fun signUp(req: Any)
    fun signIn(req: Any): TokenInfoRes
}