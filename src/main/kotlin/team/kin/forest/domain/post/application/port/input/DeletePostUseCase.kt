package team.kin.forest.domain.post.application.port.input

import java.util.*

interface DeletePostUseCase {
    fun execute(id: UUID, groupId: UUID)
}