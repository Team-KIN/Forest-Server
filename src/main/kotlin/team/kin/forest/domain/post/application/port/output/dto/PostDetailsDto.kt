package team.kin.forest.domain.post.application.port.output.dto

import team.kin.forest.domain.post.adapter.output.persistence.enums.PostTag
import java.time.LocalDateTime

data class PostDetailsDto(
    val title: String,
    val content: String,
    val tag: PostTag,
    val createdAt: LocalDateTime,
    val isModified: Boolean,
    val name: String,
    val profileUrl: String,
    val comments: List<CommentDto>
)