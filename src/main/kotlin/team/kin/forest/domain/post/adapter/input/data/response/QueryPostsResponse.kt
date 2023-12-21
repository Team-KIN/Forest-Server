package team.kin.forest.domain.post.adapter.input.data.response

import team.kin.forest.domain.post.adapter.output.persistence.enums.PostTag
import java.time.LocalDateTime
import java.util.UUID

data class QueryPostsResponse(
    val posts: List<QueryPostResponse>
) {
    data class QueryPostResponse(
        val id: UUID,
        val title: String,
        val tag: PostTag,
        val createdAt: LocalDateTime
    )
}