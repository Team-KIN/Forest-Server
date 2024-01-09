package team.kin.forest.domain.group.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.input.data.request.CreateGroupRequest
import team.kin.forest.domain.group.adapter.input.data.request.GroupCodeRequest
import team.kin.forest.domain.group.adapter.input.data.request.ModifyGroupDetailsRequest
import team.kin.forest.domain.group.adapter.input.data.response.GroupCodeResponse
import team.kin.forest.domain.group.adapter.input.data.response.QueryGroupDetailsResponse
import team.kin.forest.domain.group.adapter.input.data.response.QueryPublicGroupDetailsResponse
import team.kin.forest.domain.group.adapter.input.data.response.QueryGroupsResponse
import team.kin.forest.domain.group.application.port.input.dto.CreateGroupDto
import team.kin.forest.domain.group.application.port.input.dto.ModifyGroupDetailsDto
import team.kin.forest.domain.group.application.port.input.dto.QueryGroupDetailsDto
import team.kin.forest.domain.group.application.port.input.dto.GroupCodeDto as InputGroupCodeDto
import team.kin.forest.domain.group.application.port.output.dto.GroupCodeDto as OutputGroupCodeDto
import team.kin.forest.domain.group.application.port.output.dto.PublicGroupDetailsDto
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

    infix fun toResponse(dto: PublicGroupDetailsDto) =
        QueryPublicGroupDetailsResponse(
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

    infix fun toResponse(dto: OutputGroupCodeDto) =
        GroupCodeResponse(
            code = dto.code
        )

    infix fun toDto(request: GroupCodeRequest) =
        InputGroupCodeDto(
            code = request.code
        )

    infix fun toResponse(dto: QueryGroupDetailsDto) =
            QueryGroupDetailsResponse(
                content = dto.content,
                purpose = dto.purpose,
                code = dto.code,
                users = dto.users.map {
                    QueryGroupDetailsResponse.MemberListResponse(
                        id = it.id,
                        name = it.name,
                        profileUrl = it.profileUrl
                    )
                }
            )

    infix fun toDto(request: ModifyGroupDetailsRequest) =
        ModifyGroupDetailsDto(
            content = request.content,
            purpose = request.purpose
        )
}