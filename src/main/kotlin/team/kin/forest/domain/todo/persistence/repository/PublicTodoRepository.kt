package team.kin.forest.domain.todo.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.todo.persistence.entity.PublicTodoJpaEntity
import java.util.*

interface PublicTodoRepository : CrudRepository<PublicTodoJpaEntity, UUID>