package team.kin.forest.domain.todo.application.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.exception.MemberNotFoundException
import team.kin.forest.domain.group.application.exception.NotGroupManagerException
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.group.domain.Member
import team.kin.forest.domain.todo.adapter.output.persistence.enums.TodoType
import team.kin.forest.domain.todo.application.port.input.WritePublicTodoUseCase
import team.kin.forest.domain.todo.application.port.input.dto.CreateTodoDto
import team.kin.forest.domain.todo.application.port.output.CommandTodoPort
import team.kin.forest.domain.todo.domain.Todo
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class WritePublicTodoService(
    private val queryUserPort: QueryUserPort,
    private val queryGroupPort: QueryGroupPort,
    private val queryMemberPort: QueryMemberPort,
    private val commandTodoPort: CommandTodoPort
) : WritePublicTodoUseCase {

    override fun execute(groupId: UUID, dto: CreateTodoDto) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()
        val group = queryGroupPort.findByIdOrNull(groupId)
            ?: throw GroupNotFoundException()
        val memberScope = queryMemberPort.findMemberScopeByGroupAndUser(group, user)
        val member = Member(
            memberScope = memberScope,
            user = user,
            group = group
        )

        if (!queryMemberPort.existsMember(member)) {
            throw MemberNotFoundException()
        }
        if (group.manager != user) {
            throw NotGroupManagerException()
        }

        val todo = Todo(
            id = UUID.randomUUID(),
            content = dto.content,
            todoStatus = false,
            todoType = TodoType.PUBLIC,
            group = group
        )

        commandTodoPort.saveTodo(todo)
    }

}