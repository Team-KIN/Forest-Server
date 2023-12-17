package team.kin.forest.domain.group.adapter.output

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.mapper.GroupMapper
import team.kin.forest.domain.group.adapter.output.persistence.repository.MemberRepository
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.group.domain.Group

@Component
class QueryMemberPersistenceAdapter(
    private val memberRepository: MemberRepository,
    private val groupMapper: GroupMapper
) : QueryMemberPort {
    override fun countByGroup(group: Group): Int {
        val groupEntity = groupMapper.toEntity(group)
        return memberRepository.countByGroup(groupEntity)
    }

}