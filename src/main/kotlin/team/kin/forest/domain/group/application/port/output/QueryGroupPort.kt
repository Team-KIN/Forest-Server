package team.kin.forest.domain.group.application.port.output

import team.kin.forest.domain.group.adapter.output.persistence.enums.GroupScope
import team.kin.forest.domain.group.domain.Group
import java.util.*

interface QueryGroupPort {
    fun findByIdOrNull(id: UUID): Group?
    fun findAllByGroupScope(groupScope: GroupScope): List<Group>
}