package team.kin.forest.domain.user.application.port.output

interface SendMessagePort {

    fun sendMessage(phoneNumber: String, authCode: Int)

}