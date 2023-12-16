package team.kin.forest.domain.user.application.port.output.dto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiredAt: Long,
    val refreshTokenExpiredAt: Long
)
