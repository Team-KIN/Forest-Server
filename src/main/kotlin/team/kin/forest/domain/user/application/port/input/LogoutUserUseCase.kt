package team.kin.forest.domain.user.application.port.input

interface LogoutUserUseCase {

    fun execute(refreshToken: String)

}