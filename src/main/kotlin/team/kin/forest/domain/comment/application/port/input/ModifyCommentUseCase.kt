package team.kin.forest.domain.comment.application.port.input

import team.kin.forest.domain.comment.application.port.input.dto.ModifyCommentDto
import java.util.*

interface ModifyCommentUseCase {
    fun execute(id: UUID, groupId: UUID, postId: UUID, modifyCommentDto: ModifyCommentDto)
}