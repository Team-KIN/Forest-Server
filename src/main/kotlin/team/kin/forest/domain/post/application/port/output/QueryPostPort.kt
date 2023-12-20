package team.kin.forest.domain.post.application.port.output

import team.kin.forest.domain.post.domain.Post
import java.util.*

interface QueryPostPort {
    fun findByIdOrNull(id: UUID): Post?
    fun findAllByGroupId(groupId: UUID): List<Post>
}