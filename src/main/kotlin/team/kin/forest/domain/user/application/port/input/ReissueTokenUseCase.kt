package team.kin.forest.domain.user.application.port.input

import team.kin.forest.domain.user.application.port.output.dto.TokenDto

interface ReissueTokenUseCase {

    fun execute(refreshToken: String): TokenDto

}