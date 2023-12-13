package team.kin.forest.domain.user.adapter.output.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.concurrent.TimeUnit

@RedisHash("authentication")
data class AuthenticationRedisEntity(
    @Id
    val email: String,

    val authCodeCount: Long,

    val authenticationCount: Long,

    val isVerified: Boolean,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Long
)
