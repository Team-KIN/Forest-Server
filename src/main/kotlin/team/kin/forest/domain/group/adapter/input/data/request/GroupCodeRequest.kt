package team.kin.forest.domain.group.adapter.input.data.request

import javax.validation.constraints.NotBlank

data class GroupCodeRequest (
    @field:NotBlank
    val code: String
)