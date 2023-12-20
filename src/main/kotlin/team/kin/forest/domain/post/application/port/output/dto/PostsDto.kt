package team.kin.forest.domain.post.application.port.output.dto

import team.kin.forest.domain.post.adapter.output.persistence.enums.PostTag
import java.time.LocalDateTime
import java.util.*

data class PostsDto(
    val id: UUID,
    val title: String,
    val tag: PostTag,
    val createdAt: LocalDateTime
)