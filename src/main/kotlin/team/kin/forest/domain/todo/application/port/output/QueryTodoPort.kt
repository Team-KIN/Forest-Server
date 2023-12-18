package team.kin.forest.domain.todo.application.port.output

import java.util.*

interface QueryTodoPort {
    fun countByGroupId(groupId: UUID): Int
}