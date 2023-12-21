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
            todoStatus = domain.todoStatus,
            todoType = domain.todoType,
            group = groupMapper.toEntity(domain.group)
        )

    fun toDomain(entity: TodoJpaEntity): Todo =
        Todo(
            id = entity.id,
            content = entity.content,
            todoStatus = entity.todoStatus,
            todoType = entity.todoType,
            group = groupMapper.toDomain(entity.group)
        )

    fun toDomain(list: List<TodoJpaEntity>): List<Todo> =
        list.map {
            Todo(
                id = it.id,
                content = it.content,
                todoStatus = it.todoStatus,
                todoType = it.todoType,
                group = groupMapper.toDomain(it.group)
            )
        }

}