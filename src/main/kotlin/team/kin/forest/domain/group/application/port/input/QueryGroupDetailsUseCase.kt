package team.kin.forest.domain.group.application.port.input

import team.kin.forest.domain.group.application.port.output.dto.GroupDetailsDto
import java.util.*

interface QueryGroupDetailsUseCase {
    fun execute(id: UUID): GroupDetailsDto
}