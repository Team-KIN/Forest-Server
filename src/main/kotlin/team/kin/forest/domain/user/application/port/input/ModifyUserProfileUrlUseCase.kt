package team.kin.forest.domain.user.application.port.input

import team.kin.forest.domain.user.application.port.input.dto.ModifyUserProfileUrlDto

interface ModifyUserProfileUrlUseCase {
    fun execute(modifyUserProfileUrlDto: ModifyUserProfileUrlDto)
}