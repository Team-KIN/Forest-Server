package team.kin.forest.domain.user.application.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.input.ModifyUserNameUseCase
import team.kin.forest.domain.user.application.port.input.dto.ModifyUserNameDto
import team.kin.forest.domain.user.application.port.output.CommandUserPort
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import team.kin.forest.domain.user.domain.User

@ServiceWithTransaction
class ModifyUserNameService(
    private val queryUserPort: QueryUserPort,
    private val commandUserPort: CommandUserPort
) : ModifyUserNameUseCase {
    override fun execute(userNameDto: ModifyUserNameDto) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val modifyUser = User(
            id = user.id,
            name = userNameDto.name,
            email = user.email,
            password = user.password,
            phoneNumber = user.phoneNumber,
            profileUrl = user.profileUrl,
            authority = user.authority,
        )

        commandUserPort.saveUser(modifyUser)
    }
}