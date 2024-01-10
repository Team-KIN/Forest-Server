package team.kin.forest.domain.group.domain

import team.kin.forest.domain.group.adapter.output.persistence.enums.GroupScope
import team.kin.forest.domain.group.application.port.input.dto.ModifyGroupDetailsDto
import team.kin.forest.domain.user.domain.User
import java.util.*

class Group (
    val id: UUID,
    val name: String,
    var content: String,
    var purpose: String,
    val code: String,
    val groupScope: GroupScope,
    val manager: User
) {

    fun updateGroupDetails(dto: ModifyGroupDetailsDto): Group {
        this.content = dto.content
        this.purpose = dto.purpose
        return this
    }

}