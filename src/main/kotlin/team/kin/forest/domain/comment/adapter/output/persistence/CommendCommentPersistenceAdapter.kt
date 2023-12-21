package team.kin.forest.domain.comment.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.comment.adapter.output.persistence.mapper.CommentMapper
import team.kin.forest.domain.comment.adapter.output.persistence.repository.CommentRepository
import team.kin.forest.domain.comment.application.port.output.CommendCommentPort
import team.kin.forest.domain.comment.domain.Comment
import java.util.*

@Component
class CommendCommentPersistenceAdapter(
    private val commentRepository: CommentRepository,
    private val commentMapper: CommentMapper
) : CommendCommentPort {
    override fun saveComment(comment: Comment): UUID {
        val commentEntity = commentMapper.toEntity(comment)
        return commentRepository.save(commentEntity).id
    }
}