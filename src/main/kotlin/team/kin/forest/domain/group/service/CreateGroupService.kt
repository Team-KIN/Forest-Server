package team.kin.forest.domain.group.service

import org.apache.commons.lang3.RandomStringUtils
import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.adapter.output.persistence.enums.MemberScope
import team.kin.forest.domain.group.application.port.input.CreateGroupUseCase
import team.kin.forest.domain.group.application.port.input.dto.CreateGroupDto
import team.kin.forest.domain.group.application.port.output.CommandGroupPort
import team.kin.forest.domain.group.application.port.output.CommandMemberPort
import team.kin.forest.domain.group.application.port.output.dto.GroupCodeDto
import team.kin.forest.domain.group.domain.Group
import team.kin.forest.domain.group.domain.Member
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class CreateGroupService (
    private val queryUserPort: QueryUserPort,
    private val commandGroupPort: CommandGroupPort,
    private val commandMemberPort: CommandMemberPort
) : CreateGroupUseCase {
    override fun execute(createGroupDto: CreateGroupDto): GroupCodeDto {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val code = RandomStringUtils.random(7, true, true)

        val group = Group(
            id = UUID.randomUUID(),
            name = createGroupDto.name,
            content = createGroupDto.content,
            purpose = createGroupDto.purpose,
            groupScope = createGroupDto.groupScope,
            code = code,
            manager = user
        )

        val persistGroup = commandGroupPort.saveGroup(group)

        val member = Member(
            memberScope = MemberScope.MANAGER,
            group = persistGroup,
            user = user
        )

        commandMemberPort.saveMember(member)

        return GroupCodeDto(
            code = code
        )
    }
}