package team.kin.forest.domain.user.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.input.data.request.SignUpRequest
import team.kin.forest.domain.user.application.port.input.dto.SignUpDto

@Component
class AuthDataMapper {

    infix fun toDto(request: SignUpRequest): SignUpDto =
        SignUpDto(
            email = request.email,
            password = request.password,
            name = request.name,
            profileUrl = request.profileUrl
        )

}