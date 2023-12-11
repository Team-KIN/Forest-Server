package team.kin.forest.domain.todo.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.todo.persistence.entity.TodoJpaEntity
import java.util.*

interface TodoRepository : CrudRepository<TodoJpaEntity, UUID>