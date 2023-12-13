package team.kin.forest.domain.comment.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.comment.adapter.output.persistence.entity.CommentJpaEntity
import java.util.UUID

interface CommentRepository : CrudRepository<CommentJpaEntity, UUID>