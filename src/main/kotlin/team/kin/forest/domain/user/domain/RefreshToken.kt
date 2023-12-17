package team.kin.forest.domain.user.domain

import java.util.UUID

data class RefreshToken(
    val refreshToken: String,
    val id: UUID,
    val expiredAt: Long
)
