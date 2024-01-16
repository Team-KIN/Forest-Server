package team.kin.forest.domain.group.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.exception.MemberNotFoundException
import team.kin.forest.domain.group.application.exception.NotWithdrawGroupManagerException
import team.kin.forest.domain.group.application.port.input.GroupWithdrawPort
import team.kin.forest.domain.group.application.port.output.CommandMemberPort
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class GroupWithdrawService(
    private val queryUserPort: QueryUserPort,
    private val queryGroupPort: QueryGroupPort,
    private val queryMemberPort: QueryMemberPort,
    private val commandMemberPort: CommandMemberPort
) : GroupWithdrawPort {

    override fun execute(id: UUID) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val group = queryGroupPort.findByIdOrNull(id)
            ?: throw GroupNotFoundException()

        val member = queryMemberPort.findByGroupAndUser(group, user)

        if (!queryMemberPort.existsByUserAndGroup(user, group)) throw MemberNotFoundException()

        if (group.manager == user) throw NotWithdrawGroupManagerException()

        commandMemberPort.deleteMember(member)
    }

}