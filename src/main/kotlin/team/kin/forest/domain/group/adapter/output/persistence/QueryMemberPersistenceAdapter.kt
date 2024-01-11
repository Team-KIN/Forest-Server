package team.kin.forest.domain.group.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.enums.MemberScope
import team.kin.forest.domain.group.adapter.output.persistence.mapper.GroupMapper
import team.kin.forest.domain.group.adapter.output.persistence.mapper.MemberMapper
import team.kin.forest.domain.group.adapter.output.persistence.repository.MemberRepository
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.group.domain.Group
import team.kin.forest.domain.group.domain.Member
import team.kin.forest.domain.user.adapter.output.persistence.mapper.UserMapper
import team.kin.forest.domain.user.domain.User
import java.util.UUID

@Component
class QueryMemberPersistenceAdapter(
    private val memberRepository: MemberRepository,
    private val groupMapper: GroupMapper,
    private val userMapper: UserMapper,
    private val memberMapper: MemberMapper
) : QueryMemberPort {
    override fun countByGroupId(groupId: UUID): Int {
        return memberRepository.countByGroupId(groupId)
    }

    override fun findGroupByUserId(userId: UUID): List<Group> {
        val memberEntities = memberRepository.findAllByUserId(userId)
        val groupEntities = memberEntities.map { it.group }

        return groupEntities.map { groupMapper.toDomain(it) }
    }

    override fun existsMember(member: Member): Boolean {
        val userEntity = userMapper.toEntity(member.user)
        val groupEntity = groupMapper.toEntity(member.group)

        return memberRepository.existsByUserAndGroup(userEntity, groupEntity)
    }

    override fun findAllByGroup(group: Group): List<Member> {
        val groupEntity = groupMapper.toEntity(group)
        val memberList = memberRepository.findAllByGroup(groupEntity)
        return memberMapper.toDomain(memberList)
    }

    override fun findByGroupAndUser(group: Group, user: User): Member {
        val groupEntity = groupMapper.toEntity(group)
        val userEntity = userMapper.toEntity(user)
        val memberEntity = memberRepository.findByGroupAndUser(groupEntity, userEntity)
        return memberMapper.toDomain(memberEntity)
    }

    override fun existsAllByGroupAndMemberScope(group: Group, memberScope: MemberScope): Boolean {
        val groupEntity = groupMapper.toEntity(group)
        return memberRepository.existsAllByGroupAndMemberScope(groupEntity, memberScope)
    }

    override fun existsByUserAndGroup(user: User, group: Group): Boolean {
        val userEntity = userMapper.toEntity(user)
        val groupEntity = groupMapper.toEntity(group)
        return memberRepository.existsByUserAndGroup(userEntity, groupEntity)
    }

}