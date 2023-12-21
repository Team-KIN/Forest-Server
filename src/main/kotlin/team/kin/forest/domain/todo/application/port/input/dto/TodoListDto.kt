package team.kin.forest.domain.todo.application.port.input.dto

import java.util.UUID

data class TodoListDto(
    val publicTodos: List<PublicTodo>,
    val privateTodos: List<PrivateTodo>
) {
    data class PublicTodo(
        val id: UUID,
        val content: String,
        val achievementRate: Int,
        val todoStatus: Boolean
    )

    data class PrivateTodo(
        val id: UUID,
        val content: String,
        val todoStatus: Boolean
    )
}
