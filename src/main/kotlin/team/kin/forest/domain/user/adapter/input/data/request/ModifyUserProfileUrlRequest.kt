package team.kin.forest.domain.user.adapter.input.data.request

import org.hibernate.validator.constraints.URL
import javax.validation.constraints.NotBlank

data class ModifyUserProfileUrlRequest(
    @field:NotBlank
    @field:URL(message = "올바르지 않은 프로필 url 형식 입니다.")
    val profileUrl: String
)