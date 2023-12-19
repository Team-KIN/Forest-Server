package team.kin.forest.domain.user.application.service

import team.kin.forest.common.annotation.ServiceWithReadOnlyTransaction
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.input.QueryMyPageUseCase
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import team.kin.forest.domain.user.application.port.output.dto.UserDto

@ServiceWithReadOnlyTransaction
class QueryMyPageService(
    private val queryUserPort: QueryUserPort
) : QueryMyPageUseCase {
    override fun execute(): UserDto {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        return UserDto(
            name = user.name,
            email = user.email,
            phoneNumber = user.phoneNumber,
            profileUrl = user.profileUrl
        )
    }

}