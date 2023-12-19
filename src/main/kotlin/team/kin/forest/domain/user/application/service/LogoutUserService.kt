package team.kin.forest.domain.user.application.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.user.application.port.input.LogoutUserUseCase
import team.kin.forest.domain.user.application.port.output.CommandRefreshTokenPort
import team.kin.forest.domain.user.application.port.output.QueryRefreshTokenPort
import team.kin.forest.domain.user.application.port.output.TokenParsePort
import team.kin.forest.global.security.token.common.exception.ExpiredRefreshTokenException
import team.kin.forest.global.security.token.common.exception.InvalidTokenTypeException

@ServiceWithTransaction
class LogoutUserService(
    private val commandRefreshTokenPort: CommandRefreshTokenPort,
    private val tokenParsePort: TokenParsePort,
    private val queryRefreshTokenPort: QueryRefreshTokenPort
) : LogoutUserUseCase {

    override fun execute(refreshToken: String) {
        val parsedRefreshToken = tokenParsePort.parseRefreshToken(refreshToken)
            ?: throw InvalidTokenTypeException()

        val refreshTokenDomain = queryRefreshTokenPort.findByRefreshTokenOrNull(parsedRefreshToken)
            ?: throw ExpiredRefreshTokenException()

        commandRefreshTokenPort.deleteRefreshToken(refreshTokenDomain)
    }

}