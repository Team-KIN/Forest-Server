package team.kin.forest.domain.group.application.port.input

import java.util.UUID

interface GroupWithdrawPort {

    fun execute(id: UUID)

}