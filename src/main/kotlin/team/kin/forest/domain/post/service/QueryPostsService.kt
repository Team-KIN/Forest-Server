package team.kin.forest.domain.post.service

import team.kin.forest.common.annotation.ServiceWithReadOnlyTransaction
import team.kin.forest.domain.post.application.port.input.QueryPostsUseCase
import team.kin.forest.domain.post.application.port.output.QueryPostPort
import team.kin.forest.domain.post.application.port.output.dto.PostsDto
import java.util.*

@ServiceWithReadOnlyTransaction
class QueryPostsService(
    private val queryPostPort: QueryPostPort
) : QueryPostsUseCase {
    override fun execute(groupId: UUID): List<PostsDto> {
        val posts = queryPostPort.findAllByGroupId(groupId)
            .map {
                PostsDto(
                    id = it.id,
                    title = it.title,
                    tag = it.tag,
                    createdAt = it.createdAt,
                )
            }

        return posts
    }
}