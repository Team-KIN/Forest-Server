package team.kin.forest.domain.user.application.port.input.dto

data class ModifyUserPasswordDto(
    val currentPassword: String,
    val newPassword: String
)