package team.kin.forest.domain.group.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.repository.MemberRepository
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import java.util.UUID

@Component
class QueryMemberPersistenceAdapter(
    private val memberRepository: MemberRepository
) : QueryMemberPort {
    override fun countByGroupId(groupId: UUID): Int {
        return memberRepository.countByGroupId(groupId)
    }

}