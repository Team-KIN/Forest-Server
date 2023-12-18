package team.kin.forest.domain.group.application.port.output.dto

import java.util.*

data class GroupsDto(
    val id: UUID,
    val name: String,
    val headcount: Int,
    val todo: Int
)
