package team.kin.forest.domain.todo.domain

import team.kin.forest.common.annotation.Aggregate
import team.kin.forest.domain.user.domain.User

@Aggregate
data class PrivateTodo(
    val id: Long,
    var todoStatus: Boolean,
    val user: User,
    val todo: Todo
)