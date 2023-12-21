package team.kin.forest.domain.todo.application.port.output

import team.kin.forest.domain.todo.domain.Todo
import team.kin.forest.domain.user.domain.User

interface QueryCompletePort {

    fun countByUserAndTodo(user: User, todo: Todo?): Int

}