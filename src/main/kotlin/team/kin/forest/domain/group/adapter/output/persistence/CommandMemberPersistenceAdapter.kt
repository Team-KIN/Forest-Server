package team.kin.forest.domain.group.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.mapper.MemberMapper
import team.kin.forest.domain.group.adapter.output.persistence.repository.MemberRepository
import team.kin.forest.domain.group.application.port.output.CommandMemberPort
import team.kin.forest.domain.group.domain.Member

@Component
class CommandMemberPersistenceAdapter(
    private val memberMapper: MemberMapper,
    private val memberRepository: MemberRepository
) : CommandMemberPort {
    override fun saveMember(member: Member) {
        val memberEntity = memberMapper.toEntity(member)
        memberRepository.save(memberEntity)
    }

    override fun deleteMember(member: Member) {
        val memberEntity = memberMapper.toEntity(member)
        memberRepository.delete(memberEntity)
    }
}
