package team.kin.forest.domain.user.adapter.output.persistence.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.persistence.entity.RefreshTokenEntity
import team.kin.forest.domain.user.domain.RefreshToken

@Component
class RefreshTokenMapper {

    fun toEntity(domain: RefreshToken): RefreshTokenEntity =
        RefreshTokenEntity(
            refreshToken = domain.refreshToken,
            id = domain.id,
            expiredAt = domain.expiredAt
        )

}