package team.kin.forest.domain.group.application.port.input

import team.kin.forest.domain.group.application.port.input.dto.ModifyGroupDetailsDto
import java.util.UUID

interface ModifyGroupDetailsUseCase {

    fun execute(id: UUID, modifyGroupDetailsDto: ModifyGroupDetailsDto)

}