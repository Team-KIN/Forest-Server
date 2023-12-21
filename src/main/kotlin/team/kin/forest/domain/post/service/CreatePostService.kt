package team.kin.forest.domain.post.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.post.application.port.input.CreatePostUseCase
import team.kin.forest.domain.post.application.port.output.CommendPostPort
import team.kin.forest.domain.post.application.port.output.dto.CreatePostDto
import team.kin.forest.domain.post.domain.Post
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.time.LocalDateTime
import java.util.*

@ServiceWithTransaction
class CreatePostService(
    private val queryUserPort: QueryUserPort,
    private val queryGroupPort: QueryGroupPort,
    private val commendPostPort: CommendPostPort,
) : CreatePostUseCase {
    override fun execute(groupId: UUID, createPostDto: CreatePostDto) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val group = queryGroupPort.findByIdOrNull(groupId)
            ?: throw GroupNotFoundException()

        val post = Post(
            id = UUID.randomUUID(),
            title = createPostDto.title,
            content = createPostDto.content,
            tag = createPostDto.tag,
            group = group,
            user = user,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )

        commendPostPort.savePost(post)
    }
}