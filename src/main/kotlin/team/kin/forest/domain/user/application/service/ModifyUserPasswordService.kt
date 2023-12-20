package team.kin.forest.domain.user.application.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.user.application.exception.PasswordNotMatchException
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.input.ModifyUserPasswordUseCase
import team.kin.forest.domain.user.application.port.input.dto.ModifyUserPasswordDto
import team.kin.forest.domain.user.application.port.output.CommandUserPort
import team.kin.forest.domain.user.application.port.output.PasswordEncodePort
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import team.kin.forest.domain.user.domain.User

@ServiceWithTransaction
class ModifyUserPasswordService(
    private val passwordEncodePort: PasswordEncodePort,
    private val queryUserPort: QueryUserPort,
    private val commandUserPort: CommandUserPort
) : ModifyUserPasswordUseCase {
    override fun execute(userPasswordDto: ModifyUserPasswordDto) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        if (!passwordEncodePort.isPasswordMatch(userPasswordDto.currentPassword, user.password)) {
            throw PasswordNotMatchException()
        }

        val password = passwordEncodePort.passwordEncode(userPasswordDto.newPassword)

        val modifyUser = user.let {
            User(
                id = it.id,
                name = it.name,
                email = it.email,
                password = password,
                phoneNumber = it.phoneNumber,
                profileUrl = it.profileUrl,
                authority = it.authority,
            )
        }

        commandUserPort.saveUser(modifyUser)
    }
}