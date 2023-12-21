package team.kin.forest.domain.post.adapter.output.persistence.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.mapper.GroupMapper
import team.kin.forest.domain.post.adapter.output.persistence.entity.PostJpaEntity
import team.kin.forest.domain.post.domain.Post
import team.kin.forest.domain.user.adapter.output.persistence.mapper.UserMapper

@Component
class PostMapper(
    private val groupMapper: GroupMapper,
    private val userMapper: UserMapper
) {
    fun toDomain(entity: PostJpaEntity) = Post(
        id = entity.id,
        title = entity.title,
        content = entity.content,
        tag = entity.postTag,
        createdAt = entity.createdAt,
        modifiedAt = entity.modifiedAt,
        group = groupMapper.toDomain(entity.group),
        user = userMapper.toDomain(entity.user)
    )

    fun toDomain(entities: List<PostJpaEntity>) =
        entities.map {
            toDomain(it)
        }

    fun toEntity(dto: Post) =
        PostJpaEntity(
            id = dto.id,
            title = dto.title,
            content = dto.content,
            postTag = dto.tag,
            group = groupMapper.toEntity(dto.group),
            user = userMapper.toEntity(dto.user)
        )
}