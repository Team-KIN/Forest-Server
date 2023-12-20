package team.kin.forest.domain.post.adapter.input.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.post.adapter.input.data.QueryPostDetailsResponse
import team.kin.forest.domain.post.adapter.input.data.QueryPostsResponse
import team.kin.forest.domain.post.application.port.output.dto.PostDetailsDto
import team.kin.forest.domain.post.application.port.output.dto.PostsDto
import team.kin.forest.domain.post.adapter.input.data.QueryPostsResponse.QueryPostResponse as QueryPostResponse

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
}