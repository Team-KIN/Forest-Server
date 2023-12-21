package team.kin.forest.domain.todo.application.port.output

import team.kin.forest.domain.group.domain.Group
import team.kin.forest.domain.todo.adapter.output.persistence.enums.TodoType
import team.kin.forest.domain.todo.domain.Todo
import java.util.*

interface QueryTodoPort {
    fun countByGroupId(groupId: UUID): Int
    fun findAllByGroupAndTodoType(group: Group, todoType: TodoType): List<Todo>
}