package team.kin.forest.domain.comment.persistence.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.kin.forest.domain.comment.persistence.entity.CommentJpaEntity
import java.util.UUID

@Repository
interface CommentRepository : CrudRepository<CommentJpaEntity, UUID>