package team.kin.forest.domain.user.application.port.output

import team.kin.forest.domain.user.domain.AuthCode

interface CommandAuthCodePort {

    fun saveAuthCode(authCode: AuthCode): Int

}