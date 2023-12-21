package team.kin.forest.domain.todo.adapter.input.data.response

import java.util.*

data class TodoListResponse(
    val publicTodos: List<PublicTodoResponse>,
    val privateTodos: List<PrivateTodoResponse>
) {
    data class PublicTodoResponse(
        val id: UUID,
        val content: String,
        val achievementRate: Int,
        val todoStatus: Boolean
    )

    data class PrivateTodoResponse(
        val id: UUID,
        val content: String,
        val todoStatus: Boolean
    )
}
