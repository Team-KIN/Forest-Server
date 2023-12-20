package team.kin.forest.domain.post.application.port.input

import team.kin.forest.domain.post.application.port.output.dto.PostsDto
import java.util.*

interface QueryPostsUseCase {
    fun execute(groupId: UUID): List<PostsDto>
}