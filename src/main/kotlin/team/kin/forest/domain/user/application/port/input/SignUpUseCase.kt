package team.kin.forest.domain.user.application.port.input

import team.kin.forest.domain.user.application.port.input.dto.SignUpDto

interface SignUpUseCase {

    fun execute(dto: SignUpDto)

}