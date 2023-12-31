package team.kin.forest.domain.todo.application.port.input

import java.util.UUID

interface DeleteTodoUseCase {

    fun execute(id: UUID, todoId: UUID)

}