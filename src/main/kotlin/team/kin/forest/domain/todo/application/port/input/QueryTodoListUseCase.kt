package team.kin.forest.domain.todo.application.port.input

import team.kin.forest.domain.todo.application.port.input.dto.TodoListDto
import java.util.UUID

interface QueryTodoListUseCase {

    fun execute(groupId: UUID): TodoListDto

}