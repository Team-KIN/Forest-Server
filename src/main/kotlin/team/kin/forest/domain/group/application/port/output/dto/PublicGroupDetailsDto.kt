package team.kin.forest.domain.group.application.port.output.dto

data class PublicGroupDetailsDto (
    val name: String,
    val content: String,
    val purpose: String,
    val headcount: Int
)