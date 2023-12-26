package team.kin.forest.domain.post.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.comment.adapter.input.mapper.CommentDataMapper
import team.kin.forest.domain.post.adapter.input.data.request.CreatePostRequest
import team.kin.forest.domain.post.adapter.input.data.request.ModifyPostRequest
import team.kin.forest.domain.post.adapter.input.data.response.QueryPostDetailsResponse
import team.kin.forest.domain.post.adapter.input.data.response.QueryPostsResponse
import team.kin.forest.domain.post.application.port.input.dto.ModifyPostDto
import team.kin.forest.domain.post.application.port.output.dto.CreatePostDto
import team.kin.forest.domain.post.application.port.output.dto.PostDetailsDto
import team.kin.forest.domain.post.application.port.output.dto.PostsDto
import team.kin.forest.domain.post.adapter.input.data.response.QueryPostsResponse.QueryPostResponse as QueryPostResponse

@Component
class PostDataMapper(
    private val commentDataMapper: CommentDataMapper
) {
    infix fun toResponse(dtos: List<PostsDto>) =
        QueryPostsResponse(
            dtos.map {
                QueryPostResponse(
                    id = it.id,
                    title = it.title,
                    tag = it.tag,
                    createdAt = it.createdAt
                )
            }
        )

    infix fun toResponse(dto: PostDetailsDto) =
        QueryPostDetailsResponse(
            title = dto.title,
            content = dto.content,
            tag = dto.tag,
            createdAt = dto.createdAt,
            isModified = dto.isModified,
            name = dto.name,
            profileUrl = dto.profileUrl,
            comments = commentDataMapper toResponse dto.comments
        )

    infix fun toDto(request: CreatePostRequest) =
        CreatePostDto(
            title = request.title,
            content = request.content,
            tag = request.tag,
        )

    infix fun toDto(request: ModifyPostRequest) =
        ModifyPostDto(
            title = request.title,
            content = request.content,
        )
}