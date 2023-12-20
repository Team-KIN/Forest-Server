package team.kin.forest.domain.todo.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.todo.adapter.output.persistence.mapper.PrivateTodoMapper
import team.kin.forest.domain.todo.adapter.output.persistence.repository.PrivateTodoRepository
import team.kin.forest.domain.todo.application.port.output.CommandPrivateTodoPort
import team.kin.forest.domain.todo.domain.PrivateTodo

@Component
class CommandPrivateTodoPersistenceAdapter(
    private val privateTodoRepository: PrivateTodoRepository,
    private val privateTodoMapper: PrivateTodoMapper
) : CommandPrivateTodoPort {

    override fun savePrivateTodo(privateTodo: PrivateTodo) {
        val privateTodoEntity = privateTodoMapper.toEntity(privateTodo)
        println(privateTodoEntity.todo.id)
        privateTodoRepository.save(privateTodoEntity)
    }

}