package team.kin.forest.domain.todo.application.port.input

import team.kin.forest.domain.todo.application.port.input.dto.ModifyTodoDto
import java.util.UUID

interface ModifyTodoUseCase {

    fun execute(id: UUID, todoId: UUID, dto: ModifyTodoDto)
}