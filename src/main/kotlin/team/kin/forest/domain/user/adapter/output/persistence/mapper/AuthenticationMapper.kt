package team.kin.forest.domain.user.adapter.output.persistence.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.persistence.entity.AuthenticationRedisEntity
import team.kin.forest.domain.user.domain.Authentication

@Component
class AuthenticationMapper {

    fun toDomain(entity: AuthenticationRedisEntity?): Authentication? =
        entity?.let {
            Authentication(
                phoneNumber = it.phoneNumber,
                authCodeCount = entity.authCodeCount,
                authenticationCount = entity.authenticationCount,
                isVerified = entity.isVerified,
                expiredAt = entity.expiredAt
            )
        }

    fun toEntity(domain: Authentication): AuthenticationRedisEntity =
        AuthenticationRedisEntity(
            phoneNumber = domain.phoneNumber,
            authCodeCount = domain.authCodeCount,
            authenticationCount = domain.authenticationCount,
            isVerified = domain.isVerified,
            expiredAt = domain.expiredAt
        )

}