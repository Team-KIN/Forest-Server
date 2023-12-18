package team.kin.forest.domain.group.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.input.data.request.CreateGroupRequest
import team.kin.forest.domain.group.adapter.input.data.response.GroupCodeResponse
import team.kin.forest.domain.group.adapter.input.data.response.QueryGroupDetailsResponse
import team.kin.forest.domain.group.adapter.input.data.response.QueryGroupsResponse
import team.kin.forest.domain.group.application.port.input.dto.CreateGroupDto
import team.kin.forest.domain.group.application.port.output.dto.GroupCodeDto
import team.kin.forest.domain.group.application.port.output.dto.GroupDetailsDto
import team.kin.forest.domain.group.application.port.output.dto.GroupsDto
import team.kin.forest.domain.group.adapter.input.data.response.QueryGroupsResponse.QueryGroupResponse as QueryGroupResponse

@Component
class GroupDataMapper {

    infix fun toResponse(dtos: List<GroupsDto>) =
        QueryGroupsResponse(
            dtos.map {
                QueryGroupResponse(
                    id = it.id,
                    name = it.name,
                    headcount = it.headcount,
                    todo = it.todo
                )
            }
        )

    infix fun toResponse(dto: GroupDetailsDto) =
        QueryGroupDetailsResponse(
            name = dto.name,
            content = dto.content,
            purpose = dto.purpose,
            headcount = dto.headcount,
        )

    infix fun toDto(request: CreateGroupRequest) =
        CreateGroupDto(
            name = request.name,
            content = request.content,
            purpose = request.purpose,
            groupScope = request.groupScope
        )

    infix fun toResponse(dto: GroupCodeDto) =
        GroupCodeResponse(
            code = dto.code
        )

}