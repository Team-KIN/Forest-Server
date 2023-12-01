package team.kin.forest.domain.todo.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.user.persistence.entity.UserJpaEntity
import java.util.*

interface CompleteRepository : CrudRepository<UserJpaEntity, UUID>