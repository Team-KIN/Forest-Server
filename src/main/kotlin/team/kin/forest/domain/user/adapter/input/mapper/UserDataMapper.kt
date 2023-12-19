package team.kin.forest.domain.user.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.input.data.response.UserResponse
import team.kin.forest.domain.user.application.port.output.dto.UserDto

@Component
class UserDataMapper {
    infix fun toResponse(userDto: UserDto) = UserResponse(
        name = userDto.name,
        email = userDto.email,
        profileUrl = userDto.profileUrl
    )
}