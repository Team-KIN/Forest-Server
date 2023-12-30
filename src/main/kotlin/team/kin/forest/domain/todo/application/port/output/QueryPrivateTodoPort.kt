package team.kin.forest.domain.todo.application.port.output

import team.kin.forest.domain.todo.domain.PrivateTodo
import team.kin.forest.domain.todo.domain.Todo
import team.kin.forest.domain.user.domain.User

interface QueryPrivateTodoPort {

    fun findAllByUser(user: User): List<PrivateTodo>
    fun findByTodo(todo: Todo): PrivateTodo
    fun existsByTodoAndUser(todo: Todo, user: User): Boolean

}