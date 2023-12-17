package team.kin.forest.domain.todo.application.port.output

import team.kin.forest.domain.group.domain.Group

interface QueryTodoPort {
    fun countByGroup(group: Group): Int
}