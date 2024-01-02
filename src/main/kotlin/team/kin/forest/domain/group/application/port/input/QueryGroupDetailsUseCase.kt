package team.kin.forest.domain.group.application.port.input

import team.kin.forest.domain.group.application.port.input.dto.QueryGroupDetailsDto
import java.util.UUID

interface QueryGroupDetailsUseCase {

    fun execute(id: UUID): QueryGroupDetailsDto

}