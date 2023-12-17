package team.kin.forest.domain.user.application.port.output

import team.kin.forest.domain.user.domain.RefreshToken

interface CommandRefreshTokenPort {

    fun saveRefreshToken(refreshToken: RefreshToken): String

}