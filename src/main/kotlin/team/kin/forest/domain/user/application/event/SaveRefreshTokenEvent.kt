package team.kin.forest.domain.user.application.event

import java.util.UUID

data class SaveRefreshTokenEvent(
    val refreshToken: String,
    val id: UUID,
    val expiredAt: Long
)