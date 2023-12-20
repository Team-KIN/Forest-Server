package team.kin.forest.domain.comment.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.comment.adapter.output.persistence.mapper.CommentMapper
import team.kin.forest.domain.comment.adapter.output.persistence.repository.CommentRepository
import team.kin.forest.domain.comment.application.port.output.QueryCommentPort
import team.kin.forest.domain.comment.domain.Comment
import java.util.*

@Component
class QueryCommentPersistenceAdapter(
    private val commentRepository: CommentRepository,
    private val commentMapper: CommentMapper
) : QueryCommentPort {
    override fun findAllByPostId(groupId: UUID): List<Comment> {
        val commentEntities = commentRepository.findAllByPostId(groupId)
        return commentMapper.toDomain(commentEntities)
    }

}