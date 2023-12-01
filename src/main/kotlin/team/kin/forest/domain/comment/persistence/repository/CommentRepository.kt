package team.kin.forest.domain.comment.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.comment.persistence.entity.CommentJpaEntity
import java.util.UUID

interface CommentRepository : CrudRepository<CommentJpaEntity, UUID>