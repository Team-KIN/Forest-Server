package team.kin.forest.domain.user.adapter.input.data.request

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class SignUpRequest(
    @field:NotBlank(message = "이메일은 필수값 입니다.")
    @field:Pattern(
        regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$",
        message = "올바르지 않은 이메일 형식입니다."
    )
    val email: String,

    @field:NotBlank(message = "비밀번호는 필수값 입니다.")
    @field:Length(min = 8, max = 20, message = "비밀번호의 글자 수는 8 ~ 20자 사이여야 합니다.")
    @field:Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*?~])[a-zA-Z0-9!@#\$%^&*?~]{8,20}\$",
        message = "비밀번호는 대소문자와 특수문자를 한개씩 포함하여야 합니다."
    )
    val password: String,

    @field:NotBlank(message = "이름은 필수값 입니다.")
    @field:Length(min = 2, max = 12, message = "이름의 글자 수는 2 ~ 10자 사이여야 합니다.")
    val name: String,

    @field:NotBlank(message = "전화번호는 필수값입니다.")
    @field:Size(min = 10, max = 11, message = "전화번호의 글자 수는 10 ~ 11자 사이여야 합니다.")
    val phoneNumber: String,

    @field:NotBlank
    @field:URL(message = "올바르지 않은 프로필 url 형식 입니다.")
    val profileUrl: String
)