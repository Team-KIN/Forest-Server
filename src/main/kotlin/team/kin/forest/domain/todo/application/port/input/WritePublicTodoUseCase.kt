package team.kin.forest.domain.todo.application.port.input

import team.kin.forest.domain.todo.application.port.input.dto.CreateTodoDto
import java.util.UUID

interface WritePublicTodoUseCase {

    fun execute(groupId: UUID, dto: CreateTodoDto)

}