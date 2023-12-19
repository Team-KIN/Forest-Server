package team.kin.forest.domain.user.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.persistence.mapper.RefreshTokenMapper
import team.kin.forest.domain.user.adapter.output.persistence.repository.RefreshTokenRepository
import team.kin.forest.domain.user.application.port.output.CommandRefreshTokenPort
import team.kin.forest.domain.user.domain.RefreshToken

@Component
class CommandRefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenMapper: RefreshTokenMapper
) : CommandRefreshTokenPort {

    override fun saveRefreshToken(refreshToken: RefreshToken): String {
        val refreshTokenEntity = refreshTokenMapper.toEntity(refreshToken)
        return refreshTokenRepository.save(refreshTokenEntity).refreshToken
    }

    override fun deleteRefreshToken(refreshToken: RefreshToken) {
        val refreshTokenEntity = refreshTokenMapper.toEntity(refreshToken)
        return refreshTokenRepository.delete(refreshTokenEntity)
    }

}