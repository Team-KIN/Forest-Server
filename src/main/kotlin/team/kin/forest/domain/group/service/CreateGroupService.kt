package team.kin.forest.domain.group.service

import org.apache.commons.lang3.RandomStringUtils
import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.port.input.CreateGroupUseCase
import team.kin.forest.domain.group.application.port.input.dto.CreateGroupDto
import team.kin.forest.domain.group.application.port.output.CommandGroupPort
import team.kin.forest.domain.group.application.port.output.dto.GroupCodeDto
import team.kin.forest.domain.group.domain.Group
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class CreateGroupService (
    private val queryUserPort: QueryUserPort,
    private val commandGroupPort: CommandGroupPort
) : CreateGroupUseCase {
    override fun execute(createGroupDto: CreateGroupDto): GroupCodeDto {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val code = RandomStringUtils.random(7, false, true)

        val group = createGroupDto.let {
            Group(
                id = UUID.randomUUID(),
                name = it.name,
                content = it.content,
                purpose = it.purpose,
                groupScope = it.groupScope,
                code = code,
                manager = user
            )
        }

        commandGroupPort.saveGroup(group)

        return GroupCodeDto(
            code = code
        )
    }
}