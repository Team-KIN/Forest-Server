package team.kin.forest.domain.group.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.group.adapter.output.persistence.entity.GroupJpaEntity
import java.util.UUID

interface GroupRepository : CrudRepository<GroupJpaEntity, UUID>