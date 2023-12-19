package team.kin.forest.domain.user.adapter.output.message

import mu.KotlinLogging
import net.nurigo.sdk.NurigoApp.initialize
import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.message.exception.MessageSendFailedException
import team.kin.forest.domain.user.adapter.output.message.properties.CoolSmsProperties
import team.kin.forest.domain.user.application.port.output.SendMessagePort

private val log = KotlinLogging.logger {  }

@Component
class CoolSmsAdapter(
    private val coolSmsProperties: CoolSmsProperties
) : SendMessagePort {

    override fun sendMessage(phoneNumber: String, authCode: Int) {
        val messageService: DefaultMessageService =
            initialize(coolSmsProperties.access, coolSmsProperties.secret, "https://api.coolsms.co.kr")

        val coolsms = Message(
            from = coolSmsProperties.phoneNumber,
            to = phoneNumber,
            text = "모여봐요 공부의 숲 인증번호는 [ $authCode ] 입니다."
        )

        try {
            messageService.send(coolsms)
        } catch (e: Exception) {
            e.printStackTrace()
            log.error("coolsms message send failed")
            throw MessageSendFailedException()
        }
    }

}