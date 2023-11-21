package team.kin.forest.domain.todo.persistence.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.kin.forest.domain.todo.persistence.entity.PublicTodoJpaEntity
import java.util.*

@Repository
interface PublicTodoRepository : CrudRepository<PublicTodoJpaEntity, UUID>