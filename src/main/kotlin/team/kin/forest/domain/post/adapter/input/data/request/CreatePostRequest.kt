package team.kin.forest.domain.post.adapter.input.data.request

import org.hibernate.validator.constraints.Length
import team.kin.forest.domain.post.adapter.output.persistence.enums.PostTag
import javax.validation.constraints.NotBlank

data class CreatePostRequest (
    @field:Length(
        max = 30,
        message = "제목은 30자를 초과할 수 없습니다."
    )
    @field:NotBlank(message = "제목은 필수 값입니다.")
    val title: String,
    @field:Length(
        max = 300,
        message = "내용은 300자를 초과할 수 없습니다."
    )
    @field:NotBlank(message = "내용은 필수 값입니다.")
    val content: String,
    val tag: PostTag
)