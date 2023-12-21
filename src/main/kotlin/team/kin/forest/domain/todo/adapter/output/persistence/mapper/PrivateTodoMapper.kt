package team.kin.forest.domain.todo.adapter.output.persistence.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.todo.adapter.output.persistence.entity.PrivateTodoJpaEntity
import team.kin.forest.domain.todo.domain.PrivateTodo
import team.kin.forest.domain.user.adapter.output.persistence.mapper.UserMapper

@Component
class PrivateTodoMapper(
    private val todoMapper: TodoMapper,
    private val userMapper: UserMapper
) {

    fun toEntity(domain: PrivateTodo): PrivateTodoJpaEntity =
        PrivateTodoJpaEntity(
            id = domain.id,
            todoStatus = domain.todoStatus,
            todo = todoMapper.toEntity(domain.todo),
            user = userMapper.toEntity(domain.user)
        )

    fun toDomain(entity: PrivateTodoJpaEntity): PrivateTodo =
        PrivateTodo(
            id = entity.id,
            todoStatus = entity.todoStatus,
            todo = todoMapper.toDomain(entity.todo),
            user = userMapper.toDomain(entity.user)
        )

    fun toDomain(list: List<PrivateTodoJpaEntity>): List<PrivateTodo> =
        list.map {
            PrivateTodo(
                id = it.id,
                todoStatus = it.todoStatus,
                user = userMapper.toDomain(it.user),
                todo = todoMapper.toDomain(it.todo)
            )
        }
}