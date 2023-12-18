package team.kin.forest.domain.group.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.mapper.GroupMapper
import team.kin.forest.domain.group.adapter.output.persistence.repository.MemberRepository
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.group.domain.Group
import java.util.UUID

@Component
class QueryMemberPersistenceAdapter(
    private val memberRepository: MemberRepository,
    private val groupMapper: GroupMapper
) : QueryMemberPort {
    override fun countByGroupId(groupId: UUID): Int {
        return memberRepository.countByGroupId(groupId)
    }

    override fun findGroupByUserId(userId: UUID): List<Group> {
        val memberEntities = memberRepository.findAllByUserId(userId)
        val groupEntities = memberEntities.map { it.group }

        return groupEntities.map { groupMapper.toDomain(it) }
    }

}