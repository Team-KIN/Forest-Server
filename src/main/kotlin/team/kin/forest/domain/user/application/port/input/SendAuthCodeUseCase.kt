package team.kin.forest.domain.user.application.port.input

interface SendAuthCodeUseCase {

    fun execute(phoneNumber: String)

}