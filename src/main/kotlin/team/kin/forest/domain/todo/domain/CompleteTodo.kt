package team.kin.forest.domain.todo.domain

import team.kin.forest.domain.user.domain.User

data class CompleteTodo(
    val id: Long,
    val user: User,
    val todo: Todo
)
