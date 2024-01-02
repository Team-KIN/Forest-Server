package team.kin.forest.domain.group.application.port.input

import team.kin.forest.domain.group.application.port.output.dto.PublicGroupDetailsDto
import java.util.*

interface QueryPublicGroupDetailsUseCase {
    fun execute(id: UUID): PublicGroupDetailsDto
}