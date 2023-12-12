package team.kin.forest.domain.user.adapter.input.data.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class SignUpRequest(
    @field:NotBlank(message = "이메일은 필수값 입니다.")
    @field:Pattern(
        regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$",
        message = "올바르지 않은 이메일 형식입니다."
    )
    val email: String,

    @field:NotBlank(message = "비밀번호는 필수값 입니다.")
    @field:Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*?~])[a-zA-Z0-9!@#\$%^&*?~]{8,20}\$",
        message = "비밀번호는 대소문자와 특수문자를 한개씩 포함하여야 합니다."
    )
    val password: String,

    @field:NotBlank(message = "이름은 필수값 입니다.")
    val name: String,

    @field:NotBlank
    val profileUrl: String
)