package team.kin.forest.domain.user.application.port.output

import java.util.*

interface SecurityPort {
    fun queryCurrentUserId(): UUID
}