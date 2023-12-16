package team.kin.forest.domain.user.application.event

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import team.kin.forest.domain.user.application.port.output.CommandRefreshTokenPort
import team.kin.forest.domain.user.domain.RefreshToken

private val log = KotlinLogging.logger {  }

@Component
class SaveRefreshTokenEventHandler(
    private val commandRefreshTokenPort: CommandRefreshTokenPort
) {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun saveRefreshToken(saveRefreshTokenEvent: SaveRefreshTokenEvent) {
        log.info("saveRefreshToken is activate")

        val refreshToken = RefreshToken(
            refreshToken = saveRefreshTokenEvent.refreshToken,
            id = saveRefreshTokenEvent.id,
            expiredAt = saveRefreshTokenEvent.expiredAt
        )

        commandRefreshTokenPort.saveRefreshToken(refreshToken)
    }

}