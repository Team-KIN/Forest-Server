package team.kin.forest.domain.user.application.port.input

import team.kin.forest.domain.user.application.port.input.dto.ModifyUserPasswordDto

interface ModifyUserPasswordUseCase {
    fun execute(userPasswordDto: ModifyUserPasswordDto)
}