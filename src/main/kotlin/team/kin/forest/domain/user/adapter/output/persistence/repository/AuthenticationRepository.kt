package team.kin.forest.domain.user.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.user.adapter.output.persistence.entity.AuthenticationRedisEntity

interface AuthenticationRepository: CrudRepository<AuthenticationRedisEntity, String>