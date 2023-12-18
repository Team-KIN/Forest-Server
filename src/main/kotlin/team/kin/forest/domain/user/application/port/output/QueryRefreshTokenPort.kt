package team.kin.forest.domain.user.application.port.output

import team.kin.forest.domain.user.domain.RefreshToken

interface QueryRefreshTokenPort {

    fun findByRefreshTokenOrNull(refreshToken: String): RefreshToken?

}