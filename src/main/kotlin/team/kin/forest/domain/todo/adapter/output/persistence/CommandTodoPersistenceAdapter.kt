package team.kin.forest.domain.todo.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.todo.adapter.output.persistence.mapper.TodoMapper
import team.kin.forest.domain.todo.adapter.output.persistence.repository.TodoRepository
import team.kin.forest.domain.todo.application.port.output.CommandTodoPort
import team.kin.forest.domain.todo.domain.Todo

@Component
class CommandTodoPersistenceAdapter(
    private val todoRepository: TodoRepository,
    private val todoMapper: TodoMapper
) : CommandTodoPort {

    override fun saveTodo(todo: Todo): Todo {
        val todoEntity = todoMapper.toEntity(todo)
        return todoRepository.save(todoEntity)
            .let { todoMapper.toDomain(it) }
    }

    override fun deleteTodo(todo: Todo) {
        val todoEntity = todoMapper.toEntity(todo)
        todoRepository.delete(todoEntity)
    }

}