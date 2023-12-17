package team.kin.forest.domain.group.application.port.output

import team.kin.forest.domain.group.adapter.output.persistence.enums.GroupScope
import team.kin.forest.domain.group.domain.Group

interface QueryGroupPort {
    fun findAllByGroupScope(groupScope: GroupScope): List<Group>
}