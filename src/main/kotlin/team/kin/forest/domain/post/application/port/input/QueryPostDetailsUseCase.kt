package team.kin.forest.domain.post.application.port.input

import team.kin.forest.domain.post.application.port.output.dto.PostDetailsDto
import java.util.UUID

interface QueryPostDetailsUseCase {
    fun execute(id: UUID, groupId: UUID): PostDetailsDto
}