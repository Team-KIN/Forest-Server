package team.kin.forest.domain.main.adapter.input.data.response

import java.util.*

data class QueryMainResponse(
    val name: String,
    val email: String,
    val profileImg: String,
    val groups: List<QueryGroupResponse>
){
    data class QueryGroupResponse(
        val id: UUID,
        val name: String,
        val headcount: Int,
        val todo: Int
    )
}