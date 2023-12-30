package team.kin.forest.domain.todo.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.todo.adapter.output.persistence.mapper.PrivateTodoMapper
import team.kin.forest.domain.todo.adapter.output.persistence.mapper.TodoMapper
import team.kin.forest.domain.todo.adapter.output.persistence.repository.PrivateTodoRepository
import team.kin.forest.domain.todo.application.port.output.QueryPrivateTodoPort
import team.kin.forest.domain.todo.domain.PrivateTodo
import team.kin.forest.domain.todo.domain.Todo
import team.kin.forest.domain.user.adapter.output.persistence.mapper.UserMapper
import team.kin.forest.domain.user.domain.User

@Component
class QueryPrivateTodoPersistenceAdapter(
    private val userMapper: UserMapper,
    private val privateTodoRepository: PrivateTodoRepository,
    private val privateTodoMapper: PrivateTodoMapper,
    private val todoMapper: TodoMapper
) : QueryPrivateTodoPort {

    override fun findAllByUser(user: User): List<PrivateTodo> {
        val userEntity = userMapper.toEntity(user)
        privateTodoRepository.findAllByUser(userEntity)
            .let { return privateTodoMapper.toDomain(it) }
    }

    override fun findByTodo(todo: Todo): PrivateTodo {
        val todoEntity = todoMapper.toEntity(todo)
        privateTodoRepository.findByTodo(todoEntity)
            .let { return privateTodoMapper.toDomain(it) }
    }

    override fun existsByTodoAndUser(todo: Todo, user: User): Boolean {
        val todoEntity = todoMapper.toEntity(todo)
        val userEntity = userMapper.toEntity(user)
        return privateTodoRepository.existsByTodoAndUser(todoEntity, userEntity)
    }

}