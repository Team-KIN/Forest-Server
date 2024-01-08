package team.kin.forest.domain.group.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.exception.MemberNotFoundException
import team.kin.forest.domain.group.application.exception.NotGroupManagerException
import team.kin.forest.domain.group.application.port.input.DeleteGroupMemberUseCase
import team.kin.forest.domain.group.application.port.output.CommandMemberPort
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class DeleteGroupMemberService(
    private val queryUserPort: QueryUserPort,
    private val queryGroupPort: QueryGroupPort,
    private val queryMemberPort: QueryMemberPort,
    private val commandMemberPort: CommandMemberPort
) : DeleteGroupMemberUseCase {

    override fun execute(id: UUID, userId: UUID) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val group = queryGroupPort.findByIdOrNull(id)
            ?: throw GroupNotFoundException()

        val member = queryUserPort.findByIdOrNull(userId)
            ?: throw UserNotFoundException()

        val deleteMember = queryMemberPort.findByGroupAndUser(group, member)

        if (!queryMemberPort.existsMember(deleteMember)) throw MemberNotFoundException()

        if (group.manager != user) throw NotGroupManagerException()

        commandMemberPort.deleteMember(deleteMember)
    }

}