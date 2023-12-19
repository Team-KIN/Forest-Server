package team.kin.forest.domain.user.application.event

import mu.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import team.kin.forest.domain.user.application.port.output.CommandAuthenticationPort

private val log = KotlinLogging.logger {  }

@Component
class CreateAuthenticationEventHandler(
    private val commandAuthenticationPort: CommandAuthenticationPort
) {

    @EventListener
    fun createAuthentication(createAuthenticationEvent: CreateAuthenticationEvent) {
        log.info("createAuthenticationEvent is active")

        commandAuthenticationPort.saveAuthentication(createAuthenticationEvent.authentication)
    }

}