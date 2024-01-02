package team.kin.forest.domain.post.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.post.application.exception.ForbiddenPostException
import team.kin.forest.domain.post.application.exception.PostNotFoundException
import team.kin.forest.domain.post.application.port.input.ModifyPostUseCase
import team.kin.forest.domain.post.application.port.input.dto.ModifyPostDto
import team.kin.forest.domain.post.application.port.output.CommendPostPort
import team.kin.forest.domain.post.application.port.output.QueryPostPort
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class DeletePostService(
    private val queryPostPort: QueryPostPort,
    private val commendPostPort: CommendPostPort,
    private val queryGroupPort: QueryGroupPort,
    private val queryUserPort: QueryUserPort
) : ModifyPostUseCase {
    override fun execute(id: UUID, groupId: UUID, modifyPostDto: ModifyPostDto) {
        val group = queryGroupPort.findByIdOrNull(groupId)
            ?: throw GroupNotFoundException()

        val post = queryPostPort.findByIdOrNull(id)
            ?: throw PostNotFoundException()

        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        if(post.user.id != user.id) {
            if (group.manager.id != user.id) {
                throw ForbiddenPostException()
            }
        }

        commendPostPort.deletePost(post)
    }
}