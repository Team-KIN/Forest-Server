package team.kin.forest.domain.user.adapter.input.data.request

import javax.validation.constraints.NotBlank

data class SignInRequest(
    @field:NotBlank(message = "이메일은 필수값입니다.")
    val email: String,
    @field:NotBlank(message = "비밀번호는 필수값입니다.")
    val password: String
)
