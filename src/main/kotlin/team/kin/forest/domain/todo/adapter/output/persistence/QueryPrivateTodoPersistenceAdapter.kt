package team.kin.forest.domain.todo.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.domain.Group
import team.kin.forest.domain.todo.adapter.output.persistence.mapper.PrivateTodoMapper
import team.kin.forest.domain.todo.adapter.output.persistence.repository.PrivateTodoRepository
import team.kin.forest.domain.todo.application.port.output.QueryPrivateTodoPort
import team.kin.forest.domain.todo.domain.PrivateTodo
import team.kin.forest.domain.user.adapter.output.persistence.mapper.UserMapper
import team.kin.forest.domain.user.domain.User

@Component
class QueryPrivateTodoPersistenceAdapter(
    private val userMapper: UserMapper,
    private val privateTodoRepository: PrivateTodoRepository,
    private val privateTodoMapper: PrivateTodoMapper
) : QueryPrivateTodoPort {

    override fun findAllByUser(user: User): List<PrivateTodo> {
        val userEntity = userMapper.toEntity(user)
        privateTodoRepository.findAllByUser(userEntity)
            .let { return privateTodoMapper.toDomain(it) }
    }

}