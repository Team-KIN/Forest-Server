package team.kin.forest.domain.user.application.port.output.dto

data class UserDto (
    val name: String,
    val email: String,
    val phoneNumber: String,
    val profileUrl: String
)