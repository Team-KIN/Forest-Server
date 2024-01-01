package team.kin.forest.domain.comment.application.port.input

import java.util.*

interface DeleteCommentUseCase {
    fun execute(id: UUID, groupId: UUID, postId: UUID)
}