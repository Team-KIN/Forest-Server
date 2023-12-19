package team.kin.forest.domain.user.application.common.util

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import team.kin.forest.domain.user.application.exception.AuthenticationNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryAuthenticationPort
import team.kin.forest.domain.user.domain.Authentication

@Component
class AuthenticationValidator(
    private val queryAuthenticationPort: QueryAuthenticationPort
) {

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    fun verifyAuthenticationByEmail(phoneNumber: String): Authentication {
        val authentication = queryAuthenticationPort.findByPhoneNumberOrNull(phoneNumber)
            ?:throw AuthenticationNotFoundException()
        if (!authentication.isVerified) {
            throw AuthenticationNotFoundException()
        }
        return authentication
    }

}