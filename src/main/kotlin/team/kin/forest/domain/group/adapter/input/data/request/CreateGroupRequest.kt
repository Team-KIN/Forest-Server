package team.kin.forest.domain.group.adapter.input.data.request

import org.hibernate.validator.constraints.Length
import team.kin.forest.domain.group.adapter.output.persistence.enums.GroupScope
import javax.validation.constraints.NotBlank

data class CreateGroupRequest(
    @field:NotBlank(message = "그룹 이름은 필수값입니다.")
    @field:Length(min = 2, max = 40)
    val name: String,

    @field:NotBlank(message = "그룹 설명은 필수값입니다.")
    @field:Length(max = 100)
    val content: String,

    @field:NotBlank(message = "그룹 목적은 필수값입니다.")
    @field:Length(max = 100)
    val purpose: String,

    val groupScope: GroupScope
)
