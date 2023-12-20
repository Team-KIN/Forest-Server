package team.kin.forest.domain.comment.application.port.output

import team.kin.forest.domain.comment.domain.Comment
import java.util.UUID

interface QueryCommentPort {
    fun findAllByPostId(groupId: UUID): List<Comment>
}