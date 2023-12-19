package team.kin.forest.domain.group.application.port.input

import java.util.*

interface JoinGroupUseCase {
    fun execute(id: UUID)
}