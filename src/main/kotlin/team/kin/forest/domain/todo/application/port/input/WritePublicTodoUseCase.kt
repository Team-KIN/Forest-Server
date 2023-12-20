package team.kin.forest.domain.todo.application.port.input

import java.util.UUID

interface WritePublicTodoUseCase {

    fun execute(groupId: UUID, content: String)

}