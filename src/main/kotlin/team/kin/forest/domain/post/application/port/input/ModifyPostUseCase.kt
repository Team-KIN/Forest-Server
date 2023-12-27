package team.kin.forest.domain.post.application.port.input

import team.kin.forest.domain.post.application.port.input.dto.ModifyPostDto
import java.util.UUID

interface ModifyPostUseCase {
    fun execute(id: UUID, groupId: UUID, modifyPostDto: ModifyPostDto)
}