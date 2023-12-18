package team.kin.forest.domain.group.domain

import team.kin.forest.domain.group.adapter.output.persistence.enums.GroupScope
import team.kin.forest.domain.user.domain.User
import java.util.*

class Group (
    val id: UUID,
    val name: String,
    val content: String,
    val purpose: String,
    val code: String,
    val groupScope: GroupScope,
    val manager: User
)