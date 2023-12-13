package team.kin.forest.domain.todo.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.todo.adapter.output.persistence.entity.PublicTodoJpaEntity
import java.util.*

interface PublicTodoRepository : CrudRepository<PublicTodoJpaEntity, UUID>