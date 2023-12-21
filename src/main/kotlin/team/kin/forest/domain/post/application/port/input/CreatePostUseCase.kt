package team.kin.forest.domain.post.application.port.input

import team.kin.forest.domain.post.application.port.output.dto.CreatePostDto
import java.util.*

interface CreatePostUseCase {
    fun execute(groupId: UUID, createPostDto: CreatePostDto)
}