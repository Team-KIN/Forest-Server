package team.kin.forest.domain.group.application.port.output

import team.kin.forest.domain.group.domain.Group


interface QueryMemberPort {
    fun countByGroup(group: Group): Int
}