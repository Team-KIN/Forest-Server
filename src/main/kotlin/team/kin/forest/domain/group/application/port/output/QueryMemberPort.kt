package team.kin.forest.domain.group.application.port.output

import java.util.UUID


interface QueryMemberPort {
    fun countByGroupId(groupId: UUID): Int
}