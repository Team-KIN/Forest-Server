package team.kin.forest.domain.user.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.user.adapter.output.persistence.entity.RefreshTokenEntity

interface RefreshTokenRepository: CrudRepository<RefreshTokenEntity, String>