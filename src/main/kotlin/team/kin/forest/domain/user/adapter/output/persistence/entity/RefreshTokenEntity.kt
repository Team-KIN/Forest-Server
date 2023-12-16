package team.kin.forest.domain.user.adapter.output.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.UUID
import java.util.concurrent.TimeUnit

@RedisHash("refresh_token")
data class RefreshTokenEntity(
    @Id
    val refreshToken: String,

    val id: UUID,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Long
)