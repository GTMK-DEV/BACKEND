package gtmk.server.api.dto.users

data class TokenInfoRes(
        val grantType: String,
        val accessToken: String,
        val refreshToken: String
) {
}