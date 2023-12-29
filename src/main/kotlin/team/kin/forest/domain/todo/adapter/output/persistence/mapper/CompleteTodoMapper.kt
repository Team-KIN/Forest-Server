package team.kin.forest.domain.todo.adapter.output.persistence.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.todo.adapter.output.persistence.entity.CompleteTodoJpaEntity
import team.kin.forest.domain.todo.domain.CompleteTodo
import team.kin.forest.domain.user.adapter.output.persistence.mapper.UserMapper

@Component
class CompleteTodoMapper(
    private val userMapper: UserMapper,
    private val todoMapper: TodoMapper
) {

    fun toEntity(domain: CompleteTodo): CompleteTodoJpaEntity =
        CompleteTodoJpaEntity(
            id = domain.id,
            user = userMapper.toEntity(domain.user),
            todo = todoMapper.toEntity(domain.todo)
        )

}