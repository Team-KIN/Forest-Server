package team.kin.forest.domain.user.domain

import java.util.UUID

data class User(
    val id: UUID,
    var name: String,
    var email: String,
    var password: String,
    var phoneNumber: String,
    var profileUrl: String,
    val authority: Authority
)
