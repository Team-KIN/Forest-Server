package team.kin.forest.domain.user.application.port.input

import team.kin.forest.domain.user.application.port.input.dto.SignInDto
import team.kin.forest.domain.user.application.port.output.dto.TokenDto

interface SignInUseCase {

    fun execute(dto: SignInDto): TokenDto

}