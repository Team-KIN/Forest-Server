package team.kin.forest.domain.post.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.post.application.port.output.dto.CommentDto
import team.kin.forest.domain.post.adapter.input.data.QueryPostDetailsResponse.QueryCommentsResponse as QueryCommentsResponse

@Component
class CommentDataMapper {
    infix fun toResponse(dtos: List<CommentDto>) =
        dtos.map { this toResponse it }

    infix fun toResponse(dto: CommentDto) =
        QueryCommentsResponse(
            id = dto.id,
            content = dto.content,
            createAt = dto.createdAt,
            isModified = dto.isModified,
            name = dto.name,
            profileUrl = dto.profileUrl
        )
}