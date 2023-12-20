package team.kin.forest.domain.comment.adapter.output.persistence.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.comment.adapter.output.persistence.entity.CommentJpaEntity
import java.util.UUID

interface CommentRepository : CrudRepository<CommentJpaEntity, UUID> {
    @Query("SELECT c FROM CommentJpaEntity c WHERE c.post.id = :postId")
    fun findAllByPostId(postId: UUID): List<CommentJpaEntity>
}