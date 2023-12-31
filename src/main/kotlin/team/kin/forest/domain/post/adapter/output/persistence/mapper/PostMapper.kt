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

    fun toEntity(domain: Post) =
        PostJpaEntity(
            id = domain.id,
            title = domain.title,
            content = domain.content,
            postTag = domain.tag,
            group = groupMapper.toEntity(domain.group),
            user = userMapper.toEntity(domain.user)
        )
}