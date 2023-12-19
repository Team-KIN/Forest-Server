package team.kin.forest.domain.user.domain

import team.kin.forest.common.annotation.RootAggregate

@RootAggregate
data class AuthCode(
    val phoneNumber: String,
    val authCode: Int,
    val expiredAt: Long
)
