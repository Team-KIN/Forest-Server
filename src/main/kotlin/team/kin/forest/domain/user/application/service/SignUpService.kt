package team.kin.forest.domain.user.application.service

import org.springframework.context.ApplicationEventPublisher
import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.user.application.common.util.AuthenticationValidator
import team.kin.forest.domain.user.application.event.DeleteAuthenticationEvent
import team.kin.forest.domain.user.application.exception.DuplicateUserEmailException
import team.kin.forest.domain.user.application.port.input.SignUpUseCase
import team.kin.forest.domain.user.application.port.input.dto.SignUpDto
import team.kin.forest.domain.user.application.port.output.CommandUserPort
import team.kin.forest.domain.user.application.port.output.PasswordEncodePort
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import team.kin.forest.domain.user.domain.Authority
import team.kin.forest.domain.user.domain.User
import java.util.UUID

@ServiceWithTransaction
class SignUpService(
    private val queryUserPort: QueryUserPort,
    private val commandUserPort: CommandUserPort,
    private val passwordEncodePort: PasswordEncodePort,
    private val authenticationValidator: AuthenticationValidator,
    private val publisher: ApplicationEventPublisher
) : SignUpUseCase {

    override fun execute(dto: SignUpDto) {
        if (queryUserPort.existsByEmail(dto.email)) throw DuplicateUserEmailException()

//        val authentication = authenticationValidator.verifyAuthenticationByEmail(dto.phoneNumber)
//        val deleteAuthenticationEvent = DeleteAuthenticationEvent(authentication)
//        publisher.publishEvent(deleteAuthenticationEvent)

        val user = dto.let {
            User(
                id = UUID.randomUUID(),
                name = it.name,
                email = it.email,
                password = passwordEncodePort.passwordEncode(it.password),
                phoneNumber = it.phoneNumber,
                profileUrl = it.profileUrl,
                authority = Authority.ROLE_ACCOUNT
            )
        }
        commandUserPort.saveUser(user)
    }

}