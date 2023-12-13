package team.kin.forest.domain.user.application.port.output

import team.kin.forest.domain.user.domain.User
import java.util.UUID

interface CommandUserPort {

    fun saveUser(user: User): UUID

}