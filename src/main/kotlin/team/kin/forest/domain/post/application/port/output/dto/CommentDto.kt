package team.kin.forest.domain.post.application.port.output.dto

import java.time.LocalDateTime
import java.util.*

data class CommentDto (
    val id: UUID,
    val content: String,
    val createdAt: LocalDateTime,
    val isModified: Boolean,
    val name: String,
    val profileUrl: String,
)