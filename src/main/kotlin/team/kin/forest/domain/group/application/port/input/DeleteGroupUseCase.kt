package team.kin.forest.domain.group.application.port.input

import java.util.*

interface DeleteGroupUseCase {

    fun execute(id: UUID)

}