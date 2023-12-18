package team.kin.forest.domain.group.service

import team.kin.forest.common.annotation.ServiceWithReadOnlyTransaction
import team.kin.forest.domain.group.adapter.output.persistence.enums.GroupScope
import team.kin.forest.domain.group.application.port.input.QueryGroupsUseCase
import team.kin.forest.domain.group.application.port.output.dto.GroupsDto
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.todo.application.port.output.QueryTodoPort

@ServiceWithReadOnlyTransaction
class QueryGroupsService(
    private val queryGroupPort: QueryGroupPort,
    private val queryMemberPort: QueryMemberPort,
    private val queryTodoPort: QueryTodoPort
) : QueryGroupsUseCase {
    override fun execute(): List<GroupsDto> {
        val groups = queryGroupPort.findAllByGroupScope(GroupScope.PUBLIC)
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

        return groups
    }
}