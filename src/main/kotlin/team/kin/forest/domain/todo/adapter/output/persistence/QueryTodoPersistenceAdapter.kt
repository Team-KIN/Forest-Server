package team.kin.forest.domain.todo.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.todo.adapter.output.persistence.repository.TodoRepository
import team.kin.forest.domain.todo.application.port.output.QueryTodoPort
import java.util.*

@Component
class QueryTodoPersistenceAdapter(
    private val todoRepository: TodoRepository
) : QueryTodoPort {
    override fun countByGroupId(groupId: UUID): Int {
        return todoRepository.countByGroupId(groupId)
    }
}