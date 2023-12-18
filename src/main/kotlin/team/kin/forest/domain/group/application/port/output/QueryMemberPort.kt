package team.kin.forest.domain.group.application.port.output

import team.kin.forest.domain.group.domain.Group
import java.util.UUID


interface QueryMemberPort {
    fun countByGroupId(groupId: UUID): Int
    fun findGroupByUserId(userId: UUID): List<Group>
}