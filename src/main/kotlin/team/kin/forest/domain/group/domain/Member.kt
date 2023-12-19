package team.kin.forest.domain.group.domain

import team.kin.forest.domain.user.domain.User

data class Member(
    val id: Long = 0,
    val user: User,
    val group: Group
)
