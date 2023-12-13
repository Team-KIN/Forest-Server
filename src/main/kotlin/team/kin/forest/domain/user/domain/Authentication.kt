package team.kin.forest.domain.user.domain

data class Authentication(
    val email: String,
    val authCodeCount: Long,
    val authenticationCount: Long,
    val isVerified: Boolean,
    val expiredAt: Long
)
