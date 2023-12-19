package team.kin.forest.domain.user.application.port.output

import team.kin.forest.domain.user.domain.AuthCode

interface QueryAuthCodePort {

    fun findByPhoneNumberOrNull(phoneNumber: String): AuthCode?

}