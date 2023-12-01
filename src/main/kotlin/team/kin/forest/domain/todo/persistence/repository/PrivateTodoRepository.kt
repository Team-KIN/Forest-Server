package team.kin.forest.domain.todo.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.todo.persistence.entity.PrivateTodoJpaEntity
import java.util.*

interface PrivateTodoRepository : CrudRepository<PrivateTodoJpaEntity, UUID>