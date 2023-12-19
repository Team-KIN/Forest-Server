package team.kin.forest.domain.user.application.port.input.dto

data class SignUpDto(
    val email: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val profileUrl: String
)
