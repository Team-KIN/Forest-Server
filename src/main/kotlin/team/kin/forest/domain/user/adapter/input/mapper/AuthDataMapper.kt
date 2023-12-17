package team.kin.forest.domain.user.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.input.data.request.SignInRequest
import team.kin.forest.domain.user.adapter.input.data.request.SignUpRequest
import team.kin.forest.domain.user.adapter.input.data.response.TokenResponse
import team.kin.forest.domain.user.application.port.input.dto.SignInDto
import team.kin.forest.domain.user.application.port.input.dto.SignUpDto
import team.kin.forest.domain.user.application.port.output.dto.TokenDto
import java.time.LocalDateTime

@Component
class AuthDataMapper {

    infix fun toDto(request: SignUpRequest): SignUpDto =
        SignUpDto(
            email = request.email,
            password = request.password,
            name = request.name,
            profileUrl = request.profileUrl
        )

    infix fun toDto(request: SignInRequest): SignInDto =
        SignInDto(
            email = request.email,
            password = request.password
        )

    infix fun toResponse(dto: TokenDto): TokenResponse =
        TokenResponse(
            accessToken = dto.accessToken,
            refreshToken = dto.refreshToken,
            accessTokenExpiredAt = LocalDateTime.now().plusSeconds(dto.accessTokenExpiredAt),
            refreshTokenExpiredAt = LocalDateTime.now().plusSeconds(dto.refreshTokenExpiredAt)
        )

}