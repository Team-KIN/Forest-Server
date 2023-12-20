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

    fun toDomain(entity: MemberJpaEntity): Member =
        Member(
            id = entity.id,
            user = userMapper.toDomain(entity.user),
            group = groupMapper.toDomain(entity.group)
        )
}