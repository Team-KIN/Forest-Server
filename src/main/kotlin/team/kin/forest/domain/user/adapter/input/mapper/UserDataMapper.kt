package team.kin.forest.domain.user.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.input.data.request.ModifyUserNameRequest
import team.kin.forest.domain.user.adapter.input.data.request.ModifyUserPasswordRequest
import team.kin.forest.domain.user.adapter.input.data.request.ModifyUserProfileUrlRequest
import team.kin.forest.domain.user.adapter.input.data.response.UserResponse
import team.kin.forest.domain.user.application.port.input.dto.ModifyUserNameDto
import team.kin.forest.domain.user.application.port.input.dto.ModifyUserPasswordDto
import team.kin.forest.domain.user.application.port.input.dto.ModifyUserProfileUrlDto
import team.kin.forest.domain.user.application.port.output.dto.UserDto

@Component
class UserDataMapper {
    infix fun toResponse(userDto: UserDto) = UserResponse(
        name = userDto.name,
        email = userDto.email,
        phoneNumber = userDto.phoneNumber,
        profileUrl = userDto.profileUrl
    )

    infix fun toDto(request: ModifyUserNameRequest) = ModifyUserNameDto(
        name = request.name
    )

    infix fun toDto(request: ModifyUserPasswordRequest) = ModifyUserPasswordDto(
        currentPassword = request.currentPassword,
        newPassword = request.newPassword
    )

    infix fun toDto(request: ModifyUserProfileUrlRequest) = ModifyUserProfileUrlDto(
        profileUrl = request.profileUrl
    )
}