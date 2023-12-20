package team.kin.forest.domain.todo.application.port.input

import java.util.UUID

interface WritePrivateTodoUseCase {

    fun execute(id: UUID, content: String)

}