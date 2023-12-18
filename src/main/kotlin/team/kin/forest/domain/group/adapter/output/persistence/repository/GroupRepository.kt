package team.kin.forest.domain.group.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.group.adapter.output.persistence.entity.GroupJpaEntity
import team.kin.forest.domain.group.adapter.output.persistence.enums.GroupScope
import java.util.UUID

interface GroupRepository : CrudRepository<GroupJpaEntity, UUID> {
    fun findAllByGroupScope(group: GroupScope): List<GroupJpaEntity>
}