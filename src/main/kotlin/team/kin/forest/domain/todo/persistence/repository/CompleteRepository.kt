package team.kin.forest.domain.todo.persistence.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.kin.forest.domain.user.persistence.entity.UserJpaEntity
import java.util.*

@Repository
interface CompleteRepository : CrudRepository<UserJpaEntity, UUID>