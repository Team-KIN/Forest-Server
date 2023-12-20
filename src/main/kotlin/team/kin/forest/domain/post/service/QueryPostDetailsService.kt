package team.kin.forest.domain.post.service

import team.kin.forest.common.annotation.ServiceWithReadOnlyTransaction
import team.kin.forest.domain.comment.application.port.output.QueryCommentPort
import team.kin.forest.domain.post.application.exception.PostNotFoundException
import team.kin.forest.domain.post.application.port.input.QueryPostDetailsUseCase
import team.kin.forest.domain.post.application.port.output.QueryPostPort
import team.kin.forest.domain.post.application.port.output.dto.CommentDto
import team.kin.forest.domain.post.application.port.output.dto.PostDetailsDto
import java.util.*

@ServiceWithReadOnlyTransaction
class QueryPostDetailsService(
    private val queryCommentPort: QueryCommentPort,
    private val queryPostPort: QueryPostPort
) : QueryPostDetailsUseCase {
    override fun execute(id: UUID, groupId: UUID): PostDetailsDto {
        val post = queryPostPort.findByIdOrNull(id)
            ?: throw PostNotFoundException()

        val comments = queryCommentPort.findAllByPostId(id)

        return PostDetailsDto(
            title = post.title,
            content = post.content,
            tag = post.tag,
            createdAt = post.createdAt,
            isModified = post.modifiedAt.isAfter(post.createdAt),
            name = post.user.name,
            profileUrl = post.user.profileUrl,
            comments = comments.map { comment ->
                CommentDto(
                    id = comment.id,
                    content = comment.content,
                    createdAt = comment.createdAt,
                    isModified = comment.modifiedAt.isAfter(comment.createdAt),
                    name = comment.user.name,
                    profileUrl = comment.user.profileUrl
                )
            }
        )
    }
}