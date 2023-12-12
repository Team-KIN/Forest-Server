package team.kin.forest.domain.group.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.group.persistence.entity.GroupJpaEntity
import java.util.UUID

interface GroupRepository : CrudRepository<GroupJpaEntity, UUID>