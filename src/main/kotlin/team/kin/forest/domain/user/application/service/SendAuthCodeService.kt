package team.kin.forest.domain.user.application.service

import org.springframework.context.ApplicationEventPublisher
import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.user.application.event.CreateAuthenticationEvent
import team.kin.forest.domain.user.application.exception.TooManyAuthenticationRequestException
import team.kin.forest.domain.user.application.port.input.SendAuthCodeUseCase
import team.kin.forest.domain.user.application.port.output.CommandAuthCodePort
import team.kin.forest.domain.user.application.port.output.QueryAuthenticationPort
import team.kin.forest.domain.user.application.port.output.SendMessagePort
import team.kin.forest.domain.user.domain.AuthCode
import team.kin.forest.domain.user.domain.Authentication
import java.util.*

@ServiceWithTransaction
class SendAuthCodeService(
    private val sendMessagePort: SendMessagePort,
    private val commandAuthCodePort: CommandAuthCodePort,
    private val queryAuthenticationPort: QueryAuthenticationPort,
    private val publisher: ApplicationEventPublisher
) : SendAuthCodeUseCase {

    override fun execute(phoneNumber: String) {
        val isExistsAuthentication = queryAuthenticationPort.existsByPhoneNumber(phoneNumber)

        if (isExistsAuthentication) {
            val authentication = queryAuthenticationPort.findByPhoneNumberOrNull(phoneNumber)
            if (authentication!!.authenticationCount > 5) {
                throw TooManyAuthenticationRequestException()
            }

            publisher.publishEvent(CreateAuthenticationEvent(authentication.increaseAuthenticationCount()))
        }

        val code = createCode()
        sendMessagePort.sendMessage(phoneNumber, code)
        val authCode = AuthCode(
            phoneNumber = phoneNumber,
            authCode = code,
            expiredAt = 300
        )
        commandAuthCodePort.saveAuthCode(authCode)

        if (!isExistsAuthentication) {
            val authentication = Authentication(
                phoneNumber = phoneNumber,
                authCodeCount = 0,
                authenticationCount = 0,
                isVerified = false,
                expiredAt = Authentication.EXPIRED_AT
            )
            publisher.publishEvent(CreateAuthenticationEvent(authentication))
        }

    }

    private fun createCode() = Random().nextInt(8888) + 1111

}