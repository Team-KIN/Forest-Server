package team.kin.forest.domain.group.adapter.input.data.response

import java.util.UUID

data class QueryGroupDetailsResponse(
    val content: String,
    val purpose: String,
    val code: String,
    val users: List<MemberListResponse>
) {

    data class MemberListResponse(
        val id: UUID,
        val name: String,
        val profileUrl: String
    )

}
