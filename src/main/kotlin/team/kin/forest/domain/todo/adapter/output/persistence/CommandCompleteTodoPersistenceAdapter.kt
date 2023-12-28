package team.kin.forest.domain.todo.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.todo.adapter.output.persistence.mapper.CompleteTodoMapper
import team.kin.forest.domain.todo.adapter.output.persistence.repository.CompleteTodoRepository
import team.kin.forest.domain.todo.application.port.output.CommandCompleteTodoPort
import team.kin.forest.domain.todo.domain.CompleteTodo

@Component
class CommandCompleteTodoPersistenceAdapter(
    private val completeTodoRepository: CompleteTodoRepository,
    private val completeTodoMapper: CompleteTodoMapper
) : CommandCompleteTodoPort {

    override fun saveCompleteTodo(completeTodo: CompleteTodo) {
        val completeTodoEntity = completeTodoMapper.toEntity(completeTodo)
        completeTodoRepository.save(completeTodoEntity)
    }

}