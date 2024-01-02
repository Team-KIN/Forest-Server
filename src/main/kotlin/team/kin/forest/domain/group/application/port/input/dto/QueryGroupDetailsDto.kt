package team.kin.forest.domain.group.application.port.input.dto

import java.util.UUID

data class QueryGroupDetailsDto(
    val content: String,
    val purpose: String,
    val code: String,
    val users: List<MemberList>
) {

    data class MemberList(
        val id: UUID,
        val name: String,
        val profileUrl: String
    )

}
