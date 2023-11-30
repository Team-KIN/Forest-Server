package team.kin.forest.domain.user.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.user.persistence.entity.UserJpaEntity
import java.util.UUID

interface UserRepository : CrudRepository<UserJpaEntity, UUID>