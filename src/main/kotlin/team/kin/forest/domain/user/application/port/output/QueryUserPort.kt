package team.kin.forest.domain.user.application.port.output

interface QueryUserPort {

    fun existsByEmail(email: String): Boolean

}