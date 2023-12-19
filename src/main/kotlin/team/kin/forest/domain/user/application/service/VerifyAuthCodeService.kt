package team.kin.forest.domain.user.application.service

import org.springframework.context.ApplicationEventPublisher
import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.user.application.event.CreateAuthenticationEvent
import team.kin.forest.domain.user.application.exception.AuthCodeNotFoundException
import team.kin.forest.domain.user.application.exception.AuthCodeNotMatchException
import team.kin.forest.domain.user.application.exception.AuthenticationNotFoundException
import team.kin.forest.domain.user.application.exception.TooManyAuthCodeRequestException
import team.kin.forest.domain.user.application.port.input.VerifyAuthCodeUseCase
import team.kin.forest.domain.user.application.port.output.QueryAuthCodePort
import team.kin.forest.domain.user.application.port.output.QueryAuthenticationPort

@ServiceWithTransaction
class VerifyAuthCodeService(
    private val queryAuthCodePort: QueryAuthCodePort,
    private val queryAuthenticationPort: QueryAuthenticationPort,
    private val publisher: ApplicationEventPublisher
) : VerifyAuthCodeUseCase {

    override fun execute(authCode: Int, phoneNumber: String) {
        val authCodeDomain = queryAuthCodePort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AuthCodeNotFoundException()
        val authentication = queryAuthenticationPort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AuthenticationNotFoundException()

        if (authentication.authCodeCount > 5) throw TooManyAuthCodeRequestException()

        if (authCodeDomain.authCode != authCode) {
            publisher.publishEvent(CreateAuthenticationEvent(authentication.increaseAuthCodeCount()))
            throw AuthCodeNotMatchException()
        }

        publisher.publishEvent(CreateAuthenticationEvent(authentication.certified()))
    }

}