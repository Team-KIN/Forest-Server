package team.kin.forest.domain.group.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.input.data.response.QueryGroupsResponse
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

}