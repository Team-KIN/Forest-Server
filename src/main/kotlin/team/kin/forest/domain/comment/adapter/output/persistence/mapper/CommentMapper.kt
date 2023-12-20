package team.kin.forest.domain.comment.adapter.output.persistence.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.comment.adapter.output.persistence.entity.CommentJpaEntity
import team.kin.forest.domain.comment.domain.Comment
import team.kin.forest.domain.post.adapter.output.persistence.mapper.PostMapper
import team.kin.forest.domain.user.adapter.output.persistence.mapper.UserMapper

@Component
class CommentMapper(
    private val postMapper: PostMapper,
    private val userMapper: UserMapper
) {
    fun toDomain(entities: List<CommentJpaEntity>) =
        entities.map { toDomain(it) }

    fun toDomain(entity: CommentJpaEntity)  =
        Comment(
            id = entity.id,
            content = entity.content,
            createdAt = entity.createdAt,
            modifiedAt = entity.modifiedAt,
            post = postMapper.toDomain(entity.post),
            user = userMapper.toDomain(entity.user)
        )
}