package team.kin.forest.domain.user.application.port.output

import team.kin.forest.domain.user.domain.Authentication

interface QueryAuthenticationPort {

    fun findByPhoneNumberOrNull(phoneNumber: String): Authentication?
    fun existsByPhoneNumber(phoneNumber: String): Boolean

}