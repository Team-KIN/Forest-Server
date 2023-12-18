package team.kin.forest.domain.main.service

import team.kin.forest.common.annotation.ServiceWithReadOnlyTransaction
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.group.application.port.output.dto.GroupsDto
import team.kin.forest.domain.main.port.input.QueryMainUseCase
import team.kin.forest.domain.main.port.output.dto.MainDto
import team.kin.forest.domain.todo.application.port.output.QueryTodoPort
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort

@ServiceWithReadOnlyTransaction
class QueryMainService(
    private val queryUserPort: QueryUserPort,
    private val queryMemberPort: QueryMemberPort,
    private val queryTodoPort: QueryTodoPort
) : QueryMainUseCase {
    override fun execute(): MainDto {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val groups = queryMemberPort.findGroupByUserId(user.id)
            .map {
                val headcount = queryMemberPort.countByGroupId(it.id)
                val todo = queryTodoPort.countByGroupId(it.id)

                GroupsDto(
                    id = it.id,
                    name = it.name,
                    headcount = headcount,
                    todo = todo
                )
            }

        return MainDto(
            name = user.name,
            email = user.email,
            profileImg = user.profileUrl,
            groups = groups
        )
    }
}
