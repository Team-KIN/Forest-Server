package team.kin.forest.domain.user.application.service

import org.springframework.context.ApplicationEventPublisher
import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.user.application.event.SaveRefreshTokenEvent
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.input.ReissueTokenUseCase
import team.kin.forest.domain.user.application.port.output.QueryRefreshTokenPort
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import team.kin.forest.domain.user.application.port.output.TokenGeneratePort
import team.kin.forest.domain.user.application.port.output.TokenParsePort
import team.kin.forest.domain.user.application.port.output.dto.TokenDto
import team.kin.forest.domain.user.domain.User
import team.kin.forest.global.security.token.common.exception.ExpiredRefreshTokenException
import team.kin.forest.global.security.token.common.exception.InvalidTokenException

@ServiceWithTransaction
class ReissueTokenService(
    private val queryRefreshTokenPort: QueryRefreshTokenPort,
    private val queryUserPort: QueryUserPort,
    private val tokenGeneratePort: TokenGeneratePort,
    private val tokenParsePort: TokenParsePort,
    private val publisher: ApplicationEventPublisher
) : ReissueTokenUseCase {

    override fun execute(refreshToken: String): TokenDto {
        val parsedRefreshToken = tokenParsePort.parseRefreshToken(refreshToken)
            ?: throw InvalidTokenException()

        val refreshTokenDomain = queryRefreshTokenPort.findByRefreshTokenOrNull(parsedRefreshToken)
            ?: throw ExpiredRefreshTokenException()

        val user = queryUserPort.findByIdOrNull(refreshTokenDomain.id)
            ?: throw UserNotFoundException()

        val token = tokenGeneratePort.generateToken(refreshTokenDomain.id, user.authority)

        publishSaveRefreshToken(token, user)

        return token
    }

    private fun publishSaveRefreshToken(token: TokenDto, user: User) {
        val saveRefreshTokenEvent = SaveRefreshTokenEvent(
            token.refreshToken,
            user.id,
            token.refreshTokenExpiredAt
        )
        publisher.publishEvent(saveRefreshTokenEvent)
    }

}