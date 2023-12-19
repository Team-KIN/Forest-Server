package team.kin.forest.domain.user.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.user.adapter.output.persistence.entity.AuthCodeRedisEntity

interface AuthCodeRepository : CrudRepository<AuthCodeRedisEntity, String>