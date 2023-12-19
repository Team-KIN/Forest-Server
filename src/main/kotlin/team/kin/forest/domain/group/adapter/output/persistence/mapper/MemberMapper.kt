package team.kin.forest.domain.group.adapter.output.persistence.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.entity.MemberJpaEntity
import team.kin.forest.domain.group.domain.Member
import team.kin.forest.domain.user.adapter.output.persistence.mapper.UserMapper

@Component
class MemberMapper(
    private val groupMapper: GroupMapper,
    private val userMapper: UserMapper
) {
    fun toEntity(dto: Member) =
        MemberJpaEntity(
            id = dto.id,
            group = groupMapper.toEntity(dto.group),
            user = userMapper.toEntity(dto.user)
        )
}