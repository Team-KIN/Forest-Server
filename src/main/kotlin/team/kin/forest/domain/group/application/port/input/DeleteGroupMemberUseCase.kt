package team.kin.forest.domain.group.application.port.input

import java.util.UUID

interface DeleteGroupMemberUseCase {

    fun execute(id: UUID, userId: UUID)

}