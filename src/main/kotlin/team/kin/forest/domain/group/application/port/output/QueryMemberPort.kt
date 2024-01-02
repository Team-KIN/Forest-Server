package team.kin.forest.domain.group.application.port.output

import team.kin.forest.domain.group.domain.Group
import team.kin.forest.domain.group.domain.Member
import java.util.UUID


interface QueryMemberPort {
    fun countByGroupId(groupId: UUID): Int
    fun findGroupByUserId(userId: UUID): List<Group>
    fun existsMember(member: Member): Boolean
    fun findAllByGroup(group: Group): List<Member>
}