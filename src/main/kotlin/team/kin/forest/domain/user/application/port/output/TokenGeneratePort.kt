package team.kin.forest.domain.user.application.port.output

import team.kin.forest.domain.user.application.port.output.dto.TokenDto
import team.kin.forest.domain.user.domain.Authority
import java.util.UUID

interface TokenGeneratePort {

    fun generateToken(id: UUID, authority: Authority): TokenDto

}