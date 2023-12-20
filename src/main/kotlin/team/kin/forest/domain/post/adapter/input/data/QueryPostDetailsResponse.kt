package team.kin.forest.domain.post.adapter.input.data

import team.kin.forest.domain.post.adapter.output.persistence.enums.PostTag
import java.time.LocalDateTime
import java.util.UUID

data class QueryPostDetailsResponse(
    val title: String,
    val content: String,
    val tag: PostTag,
    val createdAt: LocalDateTime,
    val isModified: Boolean,
    val name: String,
    val profileUrl: String,
    val comments: List<QueryCommentsResponse>
) {
    data class QueryCommentsResponse(
        val id: UUID,
        val content: String,
        val createAt: LocalDateTime,
        val isModified: Boolean,
        val name: String,
        val profileUrl: String,
    )
}