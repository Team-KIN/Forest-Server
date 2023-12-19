package team.kin.forest.domain.user.adapter.input.data.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class ModifyUserPasswordRequest(
    @field:NotBlank(message = "비밀번호는 필수값 입니다.")
    @field:Length(min = 8, max = 20, message = "비밀번호의 글자 수는 8 ~ 20자 사이여야 합니다.")
    @field:Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*?~])[a-zA-Z0-9!@#\$%^&*?~]{8,20}\$",
        message = "비밀번호는 대소문자와 특수문자를 한개씩 포함하여야 합니다."
    )
    val currentPassword: String,

    @field:NotBlank(message = "비밀번호는 필수값 입니다.")
    @field:Length(min = 8, max = 20, message = "비밀번호의 글자 수는 8 ~ 20자 사이여야 합니다.")
    @field:Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*?~])[a-zA-Z0-9!@#\$%^&*?~]{8,20}\$",
        message = "비밀번호는 대소문자와 특수문자를 한개씩 포함하여야 합니다."
    )
    val newPassword: String
)
