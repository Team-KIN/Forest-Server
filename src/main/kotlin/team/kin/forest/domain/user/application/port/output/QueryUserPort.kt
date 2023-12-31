package team.kin.forest.domain.user.application.port.output

import team.kin.forest.domain.user.domain.User
import java.util.UUID

interface QueryUserPort {

    fun existsByEmail(email: String): Boolean
    fun findByEmailOrNull(email: String): User?
    fun findByIdOrNull(id: UUID): User?
    fun findCurrentUser(): User?

}