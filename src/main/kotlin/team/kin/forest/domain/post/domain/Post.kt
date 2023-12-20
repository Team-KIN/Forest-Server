package team.kin.forest.domain.post.domain

import team.kin.forest.domain.group.domain.Group
import team.kin.forest.domain.post.adapter.output.persistence.enums.PostTag
import team.kin.forest.domain.user.domain.User
import java.time.LocalDateTime
import java.util.UUID

data class Post(
    val id: UUID,
    val title: String,
    val content: String,
    val tag: PostTag,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
    val group: Group,
    val user: User
)