package team.kin.forest.domain.todo.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.mapper.GroupMapper
import team.kin.forest.domain.group.domain.Group
import team.kin.forest.domain.todo.adapter.output.persistence.repository.TodoRepository
import team.kin.forest.domain.todo.application.port.output.QueryTodoPort

@Component
class QueryTodoPersistenceAdapter(
    private val groupMapper: GroupMapper,
    private val todoRepository: TodoRepository
) : QueryTodoPort {
    override fun countByGroup(group: Group): Int {
        val groupEntity = groupMapper.toEntity(group)
        return todoRepository.countByGroup(groupEntity)
    }
}