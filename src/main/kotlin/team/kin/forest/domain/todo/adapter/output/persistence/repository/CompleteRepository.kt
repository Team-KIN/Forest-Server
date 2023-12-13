package team.kin.forest.domain.todo.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.user.adapter.output.persistence.entity.UserJpaEntity
import java.util.*

interface CompleteRepository : CrudRepository<UserJpaEntity, UUID>