package team.kin.forest.domain.group.application.port.input

import team.kin.forest.domain.group.application.port.input.dto.CreateGroupDto
import team.kin.forest.domain.group.application.port.output.dto.GroupCodeDto

interface CreateGroupUseCase {
    fun execute(createGroupDto: CreateGroupDto): GroupCodeDto
}