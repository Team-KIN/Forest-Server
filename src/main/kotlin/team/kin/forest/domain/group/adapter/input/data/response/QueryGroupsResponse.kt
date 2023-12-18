package team.kin.forest.domain.group.adapter.input.data.response

import java.util.*

data class QueryGroupsResponse(
    val groups: List<QueryGroupResponse>
){
    data class QueryGroupResponse(
        val id: UUID,
        val name: String,
        val headcount: Int,
        val todo: Int
    )
}