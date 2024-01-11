package team.kin.forest.domain.group.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.adapter.output.persistence.enums.GroupScope
import team.kin.forest.domain.group.adapter.output.persistence.enums.MemberScope
import team.kin.forest.domain.group.application.exception.AlreadyJoinGroupException
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.exception.PrivateGroupException
import team.kin.forest.domain.group.application.port.input.JoinGroupUseCase
import team.kin.forest.domain.group.application.port.output.CommandMemberPort
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.group.domain.Member
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class JoinGroupService(
    private val queryUserPort: QueryUserPort,
    private val queryMemberPort: QueryMemberPort,
    private val commandMemberPort: CommandMemberPort,
    private val queryGroupPort: QueryGroupPort
) : JoinGroupUseCase {
    override fun execute(id: UUID) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val group = queryGroupPort.findByIdOrNull(id)
            ?: throw GroupNotFoundException()

        // 비공개 그룹인지 확인
        if(group.groupScope == GroupScope.PRIVATE)
            throw PrivateGroupException()

        val member = Member(
            memberScope = MemberScope.MEMBER,
            user = user,
            group = group
        )

        // 이미 가입되어 있는지 확인
        if(queryMemberPort.existsMember(member))
            throw AlreadyJoinGroupException()

        commandMemberPort.saveMember(member)
    }
}