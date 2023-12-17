package team.kin.forest.domain.user.application.service

import org.springframework.context.ApplicationEventPublisher
import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.user.application.event.SaveRefreshTokenEvent
import team.kin.forest.domain.user.application.exception.PasswordNotMatchException
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.input.SignInUseCase
import team.kin.forest.domain.user.application.port.input.dto.SignInDto
import team.kin.forest.domain.user.application.port.output.PasswordEncodePort
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import team.kin.forest.domain.user.application.port.output.TokenGeneratePort
import team.kin.forest.domain.user.application.port.output.dto.TokenDto
import team.kin.forest.domain.user.domain.User

@ServiceWithTransaction
class SignInService(
    private val queryUserPort: QueryUserPort,
    private val passwordEncodePort: PasswordEncodePort,
    private val tokenGeneratePort: TokenGeneratePort,
    private val publisher: ApplicationEventPublisher
): SignInUseCase {

    override fun execute(dto: SignInDto): TokenDto {
        val user: User = queryUserPort.findByEmailOrNull(dto.email)
            ?: throw UserNotFoundException()

        if (!passwordEncodePort.isPasswordMatch(dto.password, user.password)) {
            throw PasswordNotMatchException()
        }

        val token = tokenGeneratePort.generateToken(user.id, user.authority)

        publishSaveRefreshToken(token, user)

        return token
    }

    private fun publishSaveRefreshToken(token: TokenDto, user: User) {
        val saveRefreshTokenEvent = SaveRefreshTokenEvent(
            refreshToken = token.refreshToken,
            id = user.id,
            expiredAt = token.refreshTokenExpiredAt
        )
        publisher.publishEvent(saveRefreshTokenEvent)
    }

}