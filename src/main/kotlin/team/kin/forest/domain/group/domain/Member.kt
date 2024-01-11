package team.kin.forest.domain.group.domain

import team.kin.forest.domain.group.adapter.output.persistence.enums.MemberScope
import team.kin.forest.domain.user.domain.User

data class Member(
    val id: Long = 0,
    val memberScope: MemberScope,
    val user: User,
    val group: Group
)
