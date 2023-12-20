package team.kin.forest.domain.todo.application.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.exception.MemberNotFoundException
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.group.domain.Member
import team.kin.forest.domain.todo.adapter.output.persistence.enums.TodoType
import team.kin.forest.domain.todo.application.port.input.WritePrivateTodoUseCase
import team.kin.forest.domain.todo.application.port.output.CommandPrivateTodoPort
import team.kin.forest.domain.todo.application.port.output.CommandTodoPort
import team.kin.forest.domain.todo.domain.PrivateTodo
import team.kin.forest.domain.todo.domain.Todo
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class WritePrivateTodoService(
    private val queryUserPort: QueryUserPort,
    private val queryGroupPort: QueryGroupPort,
    private val queryMemberPort: QueryMemberPort,
    private val commandTodoPort: CommandTodoPort,
    private val commandPrivateTodoPort: CommandPrivateTodoPort,
) : WritePrivateTodoUseCase {

    override fun execute(id: UUID, content: String) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()
        val group = queryGroupPort.findByIdOrNull(id)
            ?: throw GroupNotFoundException()
        val member = Member(
            user = user,
            group = group
        )
        if (!queryMemberPort.existsMember(member)) {
            throw MemberNotFoundException()
        }

        val todo = Todo(
            id = UUID.randomUUID(),
            content = content,
            todoType = TodoType.PRIVATE,
            group = group
        )
        val saveTodo = commandTodoPort.saveTodo(todo)

        val privateTodo = PrivateTodo(
            id = 0L,
            todoStatus = false,
            user = user,
            todo = saveTodo
        )
        commandPrivateTodoPort.savePrivateTodo(privateTodo)
    }

}