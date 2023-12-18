package team.kin.forest.domain.group.application.port.input

import team.kin.forest.domain.group.application.port.output.dto.GroupsDto

interface QueryGroupsUseCase {
    fun execute(): List<GroupsDto>
}