package team.kin.forest.domain.comment.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.comment.application.exception.CommentNotFoundException
import team.kin.forest.domain.comment.application.exception.ForbiddenCommentException
import team.kin.forest.domain.comment.application.port.input.ModifyCommentUseCase
import team.kin.forest.domain.comment.application.port.input.dto.ModifyCommentDto
import team.kin.forest.domain.comment.application.port.output.CommendCommentPort
import team.kin.forest.domain.comment.application.port.output.QueryCommentPort
import team.kin.forest.domain.comment.domain.Comment
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.post.application.exception.PostNotFoundException
import team.kin.forest.domain.post.application.port.output.QueryPostPort
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.time.LocalDateTime
import java.util.*

@ServiceWithTransaction
class ModifyCommentService(
    private val queryUserPort: QueryUserPort,
    private val queryPostPort: QueryPostPort,
    private val queryGroupPort: QueryGroupPort,
    private val queryCommentPort: QueryCommentPort,
    private val commendCommentPort: CommendCommentPort
) : ModifyCommentUseCase {
    override fun execute(id: UUID, groupId: UUID, postId: UUID, modifyCommentDto: ModifyCommentDto) {
        val group = queryGroupPort.findByIdOrNull(groupId)
            ?: throw GroupNotFoundException()

        val post = queryPostPort.findByIdOrNull(postId)
            ?: throw PostNotFoundException()

        val comment = queryCommentPort.findByIdOrNull(id)
            ?: throw CommentNotFoundException()

        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        if(comment.user.id != user.id)
            throw ForbiddenCommentException()

        val modifyComment = comment.let {
            Comment(
                id = it.id,
                content = modifyCommentDto.content,
                createdAt = it.createdAt,
                modifiedAt = LocalDateTime.now(),
                post = it.post,
                user = it.user
            )
        }

        commendCommentPort.saveComment(modifyComment)
    }
}