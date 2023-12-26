package team.kin.forest.domain.comment.adapter.output.persistence
import org.springframework.data.repository.findByIdOrNull
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
    override fun findAllByPostId(postId: UUID): List<Comment> {
        val commentEntities = commentRepository.findAllByPostId(postId)
        return commentMapper.toDomain(commentEntities)
    }

    override fun findByIdOrNull(id: UUID): Comment? {
        val commentEntity = commentRepository.findByIdOrNull(id)
        return commentEntity?.let { commentMapper.toDomain(it) }
    }

}