package team.kin.forest.domain.user.domain

data class Authentication(
    val phoneNumber: String,
    val authCodeCount: Long,
    val authenticationCount: Long,
    val isVerified: Boolean,
    val expiredAt: Long
) {

    companion object {
        const val EXPIRED_AT = 7200L
    }

    fun increaseAuthenticationCount(): Authentication =
        this.copy(
            authenticationCount = authenticationCount.inc()
        )

    fun increaseAuthCodeCount(): Authentication =
        this.copy(
            authCodeCount = authCodeCount.inc()
        )

    fun certified(): Authentication =
        this.copy(
            isVerified = true
        )
}
