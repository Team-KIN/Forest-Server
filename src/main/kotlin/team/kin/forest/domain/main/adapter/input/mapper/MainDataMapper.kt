package team.kin.forest.domain.main.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.main.adapter.input.data.response.QueryMainResponse
import team.kin.forest.domain.main.port.output.dto.MainDto
import team.kin.forest.domain.main.adapter.input.data.response.QueryMainResponse.QueryGroupResponse as QueryGroupResponse

@Component
class MainDataMapper {
    infix fun toResponse(dto: MainDto) = QueryMainResponse(
       name = dto.name,
        email = dto.email,
        profileImg = dto.profileImg,
        groups = dto.groups.map {
            QueryGroupResponse(
                id = it.id,
                name = it.name,
                headcount = it.headcount,
                todo = it.todo
            )
        }
    )
}