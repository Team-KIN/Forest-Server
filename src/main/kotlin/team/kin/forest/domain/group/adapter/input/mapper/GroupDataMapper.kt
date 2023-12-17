package team.kin.forest.domain.group.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.input.data.response.QueryGroupResponse
import team.kin.forest.domain.group.application.port.output.dto.GroupsDto

@Component
class GroupDataMapper {
    infix fun toResponse(dto: GroupsDto) = QueryGroupResponse(
        id = dto.id,
        name = dto.name,
        headcount = dto.headcount,
        todo = dto.todo
    )
}