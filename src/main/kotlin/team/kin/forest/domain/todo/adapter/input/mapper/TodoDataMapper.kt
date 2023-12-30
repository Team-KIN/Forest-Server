package team.kin.forest.domain.todo.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.todo.adapter.input.data.request.CreateTodoRequest
import team.kin.forest.domain.todo.adapter.input.data.request.ModifyTodoRequest
import team.kin.forest.domain.todo.adapter.input.data.response.TodoListResponse
import team.kin.forest.domain.todo.application.port.input.dto.CreateTodoDto
import team.kin.forest.domain.todo.application.port.input.dto.ModifyTodoDto
import team.kin.forest.domain.todo.application.port.input.dto.TodoListDto

@Component
class TodoDataMapper {

    infix fun toDto(request: CreateTodoRequest): CreateTodoDto =
        CreateTodoDto(
            content = request.content
        )

    infix fun toResponse(dto: TodoListDto): TodoListResponse =
        TodoListResponse(
            publicTodos = dto.publicTodos.map {
                TodoListResponse.PublicTodoResponse(
                    id = it.id,
                    content = it.content,
                    achievementRate = it.achievementRate,
                    todoStatus = it.todoStatus
                )
            },
            privateTodos = dto.privateTodos.map {
                TodoListResponse.PrivateTodoResponse(
                    id = it.id,
                    content = it.content,
                    todoStatus = it.todoStatus
                )
            }
        )

    infix fun toDto(request: ModifyTodoRequest): ModifyTodoDto =
        ModifyTodoDto(
            content = request.content
        )

}