package team.kin.forest.domain.todo.application.port.output

import team.kin.forest.domain.todo.domain.PrivateTodo
import team.kin.forest.domain.user.domain.User

interface QueryPrivateTodoPort {

    fun findAllByUser(user: User): List<PrivateTodo>

}