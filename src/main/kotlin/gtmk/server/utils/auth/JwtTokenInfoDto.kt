package org.example.hmsspringboot.utils.auth

class JwtTokenInfoDto(
        val grantType: String,
        val accessToken: String,
        val refreshToken: String
) {
}