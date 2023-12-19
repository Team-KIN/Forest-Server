package team.kin.forest.domain.user.application.port.output

import team.kin.forest.domain.user.domain.Authentication

interface CommandAuthenticationPort {

    fun saveAuthentication(authentication: Authentication)
    fun deleteAuthentication(authentication: Authentication)

}