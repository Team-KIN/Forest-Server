package team.kin.forest.domain.todo.adapter.output.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.mapper.GroupMapper
import team.kin.forest.domain.group.domain.Group
import team.kin.forest.domain.todo.adapter.output.persistence.enums.TodoType
import team.kin.forest.domain.todo.adapter.output.persistence.mapper.TodoMapper
import team.kin.forest.domain.todo.adapter.output.persistence.repository.TodoRepository
import team.kin.forest.domain.todo.application.port.output.QueryTodoPort
import team.kin.forest.domain.todo.domain.Todo
import java.util.*

@Component
class QueryTodoPersistenceAdapter(
    private val todoRepository: TodoRepository,
    private val groupMapper: GroupMapper,
    private val todoMapper: TodoMapper
) : QueryTodoPort {
    override fun countByGroupId(groupId: UUID): Int {
        return todoRepository.countByGroupId(groupId)
    }

    override fun findAllByGroupAndTodoType(group: Group, todoType: TodoType): List<Todo> {
        val groupEntity = groupMapper.toEntity(group)
        todoRepository.findAllByGroupAndTodoType(groupEntity, todoType)
            .let { return todoMapper.toDomain(it) }
    }

    override fun findByIdAndTodoType(id: UUID, todoType: TodoType): Todo? {
        val todoEntity = todoRepository.findByIdAndTodoType(id, todoType)
        return todoEntity?.let { todoMapper.toDomain(it) }
    }
}