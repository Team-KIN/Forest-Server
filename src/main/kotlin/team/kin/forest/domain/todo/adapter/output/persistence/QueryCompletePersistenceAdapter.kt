package team.kin.forest.domain.todo.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.todo.adapter.output.persistence.mapper.TodoMapper
import team.kin.forest.domain.todo.adapter.output.persistence.repository.CompleteTodoRepository
import team.kin.forest.domain.todo.application.port.output.QueryCompleteTodoPort
import team.kin.forest.domain.todo.domain.Todo
import team.kin.forest.domain.user.adapter.output.persistence.mapper.UserMapper
import team.kin.forest.domain.user.domain.User

@Component
class QueryCompletePersistenceAdapter(
    private val completeTodoRepository: CompleteTodoRepository,
    private val userMapper: UserMapper,
    private val todoMapper: TodoMapper
) : QueryCompleteTodoPort{

    override fun countByUserAndTodo(user: User, todo: Todo?): Int {
        val userEntity = userMapper.toEntity(user)
        val todoEntity = todo?.let { todoMapper.toEntity(it) }
        return completeTodoRepository.countByUserAndTodo(userEntity, todoEntity)
    }

    override fun existsByTodo(todo: Todo): Boolean {
        val todoEntity = todoMapper.toEntity(todo)
        return completeTodoRepository.existsByTodo(todoEntity)
    }

}