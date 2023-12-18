package team.kin.forest.domain.main.port.output.dto

import team.kin.forest.domain.group.application.port.output.dto.GroupsDto

data class MainDto(
    val name: String,
    val email: String,
    val profileImg: String,
    val groups: List<GroupsDto>
)