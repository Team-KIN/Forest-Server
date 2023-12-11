package team.kin.forest.domain.todo.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.user.persistence.entity.UserJpaEntity

interface CompleteRepository : CrudRepository<UserJpaEntity, Long>