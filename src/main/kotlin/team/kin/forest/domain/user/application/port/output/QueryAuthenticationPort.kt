package team.kin.forest.domain.user.application.port.output

import team.kin.forest.domain.user.domain.Authentication

interface QueryAuthenticationPort {

    fun findByEmailOrNull(email: String): Authentication?

}