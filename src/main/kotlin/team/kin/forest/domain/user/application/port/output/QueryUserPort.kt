package team.kin.forest.domain.user.application.port.output

import team.kin.forest.domain.user.domain.User

interface QueryUserPort {

    fun existsByEmail(email: String): Boolean
    fun findByEmailOrNull(email: String): User?
    fun findCurrentUser(): User?

}