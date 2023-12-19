package team.kin.forest.domain.user.application.port.input

interface VerifyAuthCodeUseCase {

    fun execute(authCode: Int, phoneNumber: String)

}