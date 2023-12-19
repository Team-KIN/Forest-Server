package team.kin.forest.domain.user.adapter.input.data.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class ModifyUserNameRequest(
    @field:NotBlank(message = "이름은 필수값 입니다.")
    @field:Length(min = 2, max = 12, message = "이름의 글자 수는 2 ~ 10자 사이여야 합니다.")
    val name: String
)