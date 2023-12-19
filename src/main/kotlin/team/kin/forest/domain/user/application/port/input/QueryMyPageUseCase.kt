package team.kin.forest.domain.user.application.port.input

import team.kin.forest.domain.user.application.port.output.dto.UserDto

interface QueryMyPageUseCase {
    fun execute(): UserDto
}