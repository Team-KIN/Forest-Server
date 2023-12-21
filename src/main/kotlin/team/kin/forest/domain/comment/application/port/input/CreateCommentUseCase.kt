package team.kin.forest.domain.comment.application.port.input

import team.kin.forest.domain.comment.application.port.input.dto.CreateCommentDto
import java.util.*

interface CreateCommentUseCase {
    fun execute(groupId: UUID, postId: UUID, createCommentDto: CreateCommentDto)
}