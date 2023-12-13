package team.kin.forest.domain.user.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.user.adapter.output.persistence.entity.UserJpaEntity
import java.util.UUID

interface UserRepository : CrudRepository<UserJpaEntity, UUID> {

    fun existsByEmail(email: String): Boolean

}