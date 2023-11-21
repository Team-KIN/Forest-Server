package team.kin.forest.domain.user.persistence.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.kin.forest.domain.user.persistence.entity.UserJpaEntity
import java.util.UUID

@Repository
interface UserRepository : CrudRepository<UserJpaEntity, UUID>