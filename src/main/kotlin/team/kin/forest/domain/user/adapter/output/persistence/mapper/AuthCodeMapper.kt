package team.kin.forest.domain.user.adapter.output.persistence.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.persistence.entity.AuthCodeRedisEntity
import team.kin.forest.domain.user.domain.AuthCode

@Component
class AuthCodeMapper {

    fun toEntity(authCode: AuthCode): AuthCodeRedisEntity =
        AuthCodeRedisEntity(
            authCode.phoneNumber,
            authCode.authCode,
            authCode.expiredAt
        )

}