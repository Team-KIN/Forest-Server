package team.kin.forest.domain.comment.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.comment.application.port.input.CreateCommentUseCase
import team.kin.forest.domain.comment.application.port.input.dto.CreateCommentDto
import team.kin.forest.domain.comment.application.port.output.CommendCommentPort
import team.kin.forest.domain.comment.domain.Comment
import team.kin.forest.domain.post.application.exception.PostNotFoundException
import team.kin.forest.domain.post.application.port.output.QueryPostPort
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.time.LocalDateTime
import java.util.*

@ServiceWithTransaction
class CreateCommentService(
    private val queryUserPort: QueryUserPort,
    private val queryPostPort: QueryPostPort,
    private val commendCommentPort: CommendCommentPort
) : CreateCommentUseCase {
    override fun execute(groupId: UUID, postId: UUID, createCommentDto: CreateCommentDto) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()
        val post = queryPostPort.findByIdOrNull(postId)
            ?: throw PostNotFoundException()

        val comment = Comment(
            id = UUID.randomUUID(),
            content = createCommentDto.content,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now(),
            user = user,
            post = post
        )

        commendCommentPort.saveComment(comment)
    }
}