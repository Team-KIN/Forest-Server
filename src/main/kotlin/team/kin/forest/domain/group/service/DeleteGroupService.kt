package team.kin.forest.domain.group.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.adapter.output.persistence.enums.MemberScope
import team.kin.forest.domain.group.application.exception.ExistGroupMemberException
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.exception.NotGroupManagerException
import team.kin.forest.domain.group.application.port.input.DeleteGroupUseCase
import team.kin.forest.domain.group.application.port.output.CommandGroupPort
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class DeleteGroupService(
    private val queryUserPort: QueryUserPort,
    private val queryGroupPort: QueryGroupPort,
    private val queryMemberPort: QueryMemberPort,
    private val commandGroupPort: CommandGroupPort
) : DeleteGroupUseCase {

    override fun execute(id: UUID) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val group = queryGroupPort.findByIdOrNull(id)
            ?: throw GroupNotFoundException()

        if (queryMemberPort.existsAllByGroupAndMemberScope(group, MemberScope.MEMBER)) throw ExistGroupMemberException()

        if (group.manager != user) throw NotGroupManagerException()

        commandGroupPort.deleteGroup(group)

    }

}