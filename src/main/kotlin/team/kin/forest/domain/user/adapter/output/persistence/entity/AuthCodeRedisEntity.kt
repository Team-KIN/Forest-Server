package team.kin.forest.domain.user.adapter.output.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.concurrent.TimeUnit

@RedisHash("auth_code")
data class AuthCodeRedisEntity(
    @Id
    val phoneNumber: String,

    val authCode: Int,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Long
)
