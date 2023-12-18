package team.kin.forest.domain.group.application.port.input.dto

import team.kin.forest.domain.group.adapter.output.persistence.enums.GroupScope

data class CreateGroupDto (
    val name: String,
    val content: String,
    val purpose: String,
    val GroupScope: GroupScope
)