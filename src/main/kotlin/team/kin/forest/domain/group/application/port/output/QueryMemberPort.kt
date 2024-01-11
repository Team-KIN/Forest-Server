package team.kin.forest.domain.group.application.port.output

import team.kin.forest.domain.group.adapter.output.persistence.enums.MemberScope
import team.kin.forest.domain.group.domain.Group
import team.kin.forest.domain.group.domain.Member
import team.kin.forest.domain.user.domain.User
import java.util.UUID


interface QueryMemberPort {
    fun countByGroupId(groupId: UUID): Int
    fun findGroupByUserId(userId: UUID): List<Group>
    fun existsMember(member: Member): Boolean
    fun findAllByGroup(group: Group): List<Member>
    fun findByGroupAndUser(group: Group, user: User): Member
    fun findMemberScopeByGroupAndUser(group: Group, user: User): MemberScope
}