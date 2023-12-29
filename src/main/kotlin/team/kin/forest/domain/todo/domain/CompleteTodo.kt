package team.kin.forest.domain.todo.domain

import team.kin.forest.common.annotation.RootAggregate
import team.kin.forest.domain.user.domain.User

@RootAggregate
data class CompleteTodo(
    val id: Long,
    val user: User,
    val todo: Todo
)
