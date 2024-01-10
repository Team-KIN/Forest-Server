package team.kin.forest.domain.group.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.exception.NotGroupManagerException
import team.kin.forest.domain.group.application.port.input.ModifyGroupDetailsUseCase
import team.kin.forest.domain.group.application.port.input.dto.ModifyGroupDetailsDto
import team.kin.forest.domain.group.application.port.output.CommandGroupPort
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class ModifyGroupDetailsService(
    private val queryUserPort: QueryUserPort,
    private val queryGroupPort: QueryGroupPort,
    private val commandGroupPort: CommandGroupPort
) : ModifyGroupDetailsUseCase {

    override fun execute(id: UUID, modifyGroupDetailsDto: ModifyGroupDetailsDto) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val group = queryGroupPort.findByIdOrNull(id)
            ?: throw GroupNotFoundException()

        if (group.manager != user) throw NotGroupManagerException()

        commandGroupPort.saveGroup(group.updateGroupDetails(modifyGroupDetailsDto))
    }

}