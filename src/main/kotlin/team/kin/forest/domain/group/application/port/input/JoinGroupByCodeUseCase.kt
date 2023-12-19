package team.kin.forest.domain.group.application.port.input

import team.kin.forest.domain.group.application.port.input.dto.GroupCodeDto

interface JoinGroupByCodeUseCase {
    fun execute(groupCodeDto: GroupCodeDto)
}