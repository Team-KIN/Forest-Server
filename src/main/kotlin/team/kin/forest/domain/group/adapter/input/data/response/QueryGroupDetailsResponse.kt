package team.kin.forest.domain.group.adapter.input.data.response

data class QueryGroupDetailsResponse (
    val name: String,
    val content: String,
    val purpose: String,
    val headcount: Int
)