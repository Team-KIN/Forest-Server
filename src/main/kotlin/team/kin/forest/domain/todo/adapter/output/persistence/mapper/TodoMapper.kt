package team.kin.forest.domain.todo.adapter.output.persistence.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.mapper.GroupMapper
import team.kin.forest.domain.todo.adapter.output.persistence.entity.TodoJpaEntity
import team.kin.forest.domain.todo.domain.Todo

@Component
class TodoMapper(
    private val groupMapper: GroupMapper,
) {

    fun toEntity(domain: Todo): TodoJpaEntity =
        TodoJpaEntity(
            id = domain.id,
            content = domain.content,
            todoType = domain.todoType,
            group = groupMapper.toEntity(domain.group)
        )

    fun toDomain(entity: TodoJpaEntity): Todo =
        Todo(
            id = entity.id,
            content = entity.content,
            todoType = entity.todoType,
            group = groupMapper.toDomain(entity.group)
        )

}