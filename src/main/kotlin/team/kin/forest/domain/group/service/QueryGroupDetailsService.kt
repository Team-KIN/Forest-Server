package team.kin.forest.domain.group.service

import team.kin.forest.common.annotation.ServiceWithReadOnlyTransaction
import team.kin.forest.domain.group.adapter.output.persistence.enums.GroupScope
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.exception.PrivateGroupException
import team.kin.forest.domain.group.application.port.input.QueryGroupDetailsUseCase
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.group.application.port.output.dto.GroupDetailsDto
import java.util.*

@ServiceWithReadOnlyTransaction
class QueryGroupDetailsService(
    private val groupPort: QueryGroupPort,
    private val memberPort: QueryMemberPort
) : QueryGroupDetailsUseCase {
    override fun execute(id: UUID): GroupDetailsDto {
        val group = groupPort.findByIdOrNull(id)
            ?: throw GroupNotFoundException()

        if(group.groupScope == GroupScope.PRIVATE)
            throw PrivateGroupException()

        val headcount = memberPort.countByGroupId(group.id)

        return GroupDetailsDto(
            name = group.name,
            content= group.content,
            purpose = group.purpose,
            headcount = headcount
        )
    }
}