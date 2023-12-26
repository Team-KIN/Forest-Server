package team.kin.forest.domain.comment.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.comment.adapter.input.data.request.CreateCommentRequest
import team.kin.forest.domain.comment.adapter.input.data.request.ModifyCommentRequest
import team.kin.forest.domain.comment.application.port.input.dto.CreateCommentDto
import team.kin.forest.domain.post.application.port.output.dto.CommentDto
import team.kin.forest.domain.post.adapter.input.data.response.QueryPostDetailsResponse.QueryCommentsResponse as QueryCommentsResponse

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

    infix fun toDto(request: CreateCommentRequest) =
        CreateCommentDto(
            content = request.content
        )

    infix fun toDto(request: ModifyCommentRequest) =
        ModifyCommentRequest(
            content = request.content
        )
}