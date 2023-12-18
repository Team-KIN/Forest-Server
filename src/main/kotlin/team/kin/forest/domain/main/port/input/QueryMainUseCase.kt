package team.kin.forest.domain.main.port.input

import team.kin.forest.domain.main.port.output.dto.MainDto

interface QueryMainUseCase {
    fun execute(): MainDto
}