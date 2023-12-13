package team.kin.forest.domain.user.application.event

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import team.kin.forest.domain.user.application.port.output.CommandAuthenticationPort

private val log = KotlinLogging.logger {  }

@Component
class DeleteAuthenticationEventHandler(
    private val commandAuthenticationPort: CommandAuthenticationPort
) {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun deleteAuthentication(deleteAuthenticationEvent: DeleteAuthenticationEvent) {
        log.info("deleteAuthenticationEvent is active")

        commandAuthenticationPort.deleteAuthentication(deleteAuthenticationEvent.authentication)
    }

}