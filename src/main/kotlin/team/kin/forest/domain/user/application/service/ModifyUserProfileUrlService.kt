package team.kin.forest.domain.user.application.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.input.ModifyUserProfileUrlUseCase
import team.kin.forest.domain.user.application.port.input.dto.ModifyUserProfileUrlDto
import team.kin.forest.domain.user.application.port.output.CommandUserPort
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import team.kin.forest.domain.user.domain.User

@ServiceWithTransaction
class ModifyUserProfileUrlService(
    private val queryUserPort: QueryUserPort,
    private val commandUserPort: CommandUserPort
) : ModifyUserProfileUrlUseCase {
    override fun execute(modifyUserProfileUrlDto: ModifyUserProfileUrlDto) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val modifyUser = user.let {
            User(
                id = it.id,
                name = it.name,
                email = it.email,
                password = it.password,
                phoneNumber = it.phoneNumber,
                profileUrl = modifyUserProfileUrlDto.profileUrl,
                authority = it.authority,
            )
        }

        commandUserPort.saveUser(modifyUser)
    }
}