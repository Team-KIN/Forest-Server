package team.kin.forest.domain.group.adapter.output.persistence.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.entity.GroupJpaEntity
import team.kin.forest.domain.group.domain.Group
import team.kin.forest.domain.user.adapter.output.persistence.mapper.UserMapper

@Component
class GroupMapper (
    private val userMapper: UserMapper
){
    fun toDomain(entity: GroupJpaEntity) = entity.run {
        Group(
            id = id,
            name = name,
            content = content,
            purpose = purpose,
            code = code,
            groupScope = groupScope,
            manager = userMapper.toDomain(manager)
        )
    }

    fun toDomain(entities: List<GroupJpaEntity>) = entities.map {
        Group(
            id = it.id,
            name = it.name,
            content = it.content,
            purpose = it.purpose,
            code = it.code,
            groupScope = it.groupScope,
            manager = userMapper.toDomain(it.manager)
        )
    }

fun toEntity(dto: Group) =
        GroupJpaEntity(
            id = dto.id,
            name = dto.name,
            content = dto.content,
            purpose = dto.purpose,
            code = dto.code,
            groupScope = dto.groupScope,
            manager = userMapper.toEntity(dto.manager)
        )
