package team.kin.forest.domain.user.application.port.output

interface PasswordEncodePort {

    fun passwordEncode(password: String): String

}