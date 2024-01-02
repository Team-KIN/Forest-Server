package team.kin.forest.domain.group.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.exception.NotGroupManagerException
import team.kin.forest.domain.group.application.port.input.QueryGroupDetailsUseCase
import team.kin.forest.domain.group.application.port.input.dto.QueryGroupDetailsDto
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class QueryGroupDetailsService(
    private val queryUserPort: QueryUserPort,
    private val queryGroupPort: QueryGroupPort,
    private val queryMemberPort: QueryMemberPort
) : QueryGroupDetailsUseCase {

    override fun execute(id: UUID): QueryGroupDetailsDto {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val group = queryGroupPort.findByIdOrNull(id)
            ?: throw GroupNotFoundException()

        if (group.manager != user) throw NotGroupManagerException()

        val memberList = queryMemberPort.findAllByGroup(group)

        return QueryGroupDetailsDto(
            content = group.content,
            purpose = group.purpose,
            code = group.code,
            users = memberList.map {
                QueryGroupDetailsDto.MemberList(
                    id = it.user.id,
                    name = it.user.name,
                    profileUrl = it.user.profileUrl
                )
            }
        )
    }

}