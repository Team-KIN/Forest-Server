package team.kin.forest.domain.user.application.port.input

import team.kin.forest.domain.user.application.port.input.dto.ModifyUserNameDto

interface ModifyUserNameUseCase {
    fun execute(userNameDto: ModifyUserNameDto)
}