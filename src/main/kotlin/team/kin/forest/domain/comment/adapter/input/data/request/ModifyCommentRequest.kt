package team.kin.forest.domain.comment.adapter.input.data.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class ModifyCommentRequest(
    @field:Length(
        max = 150,
        message = "댓글은 150자를 초과할 수 없습니다."
    )
    @field:NotBlank(message = "댓글은 필수 값입니다.")
    val content: String
)