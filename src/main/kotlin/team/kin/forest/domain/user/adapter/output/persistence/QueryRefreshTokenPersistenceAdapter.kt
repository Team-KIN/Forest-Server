package team.kin.forest.domain.user.adapter.output.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.persistence.mapper.RefreshTokenMapper
import team.kin.forest.domain.user.adapter.output.persistence.repository.RefreshTokenRepository
import team.kin.forest.domain.user.application.port.output.QueryRefreshTokenPort
import team.kin.forest.domain.user.domain.RefreshToken

@Component
class QueryRefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenMapper: RefreshTokenMapper
) : QueryRefreshTokenPort {

    override fun findByRefreshTokenOrNull(refreshToken: String): RefreshToken? {
        val refreshTokenEntity = refreshTokenRepository.findByIdOrNull(refreshToken)
        return refreshTokenEntity?.let { refreshTokenMapper.toDomain(it) }
    }

}