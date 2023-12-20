package team.kin.forest.domain.comment.domain

import team.kin.forest.domain.post.domain.Post
import team.kin.forest.domain.user.domain.User
import java.time.LocalDateTime
import java.util.UUID

class Comment (
    val id: UUID,
    val content: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
    val post: Post,
    val user: User
)