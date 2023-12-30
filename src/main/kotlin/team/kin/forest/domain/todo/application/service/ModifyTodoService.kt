package team.kin.forest.domain.todo.application.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.todo.adapter.output.persistence.enums.TodoType
import team.kin.forest.domain.todo.application.exception.AlreadyCompleteTodoException
import team.kin.forest.domain.todo.application.exception.NotTodoOwnerException
import team.kin.forest.domain.todo.application.exception.TodoNotFoundException
import team.kin.forest.domain.todo.application.port.input.ModifyTodoUseCase
import team.kin.forest.domain.todo.application.port.input.dto.ModifyTodoDto
import team.kin.forest.domain.todo.application.port.output.CommandTodoPort
import team.kin.forest.domain.todo.application.port.output.QueryCompleteTodoPort
import team.kin.forest.domain.todo.application.port.output.QueryPrivateTodoPort
import team.kin.forest.domain.todo.application.port.output.QueryTodoPort
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class ModifyTodoService(
    private val queryUserPort: QueryUserPort,
    private val queryPrivateTodoPort: QueryPrivateTodoPort,
    private val queryGroupPort: QueryGroupPort,
    private val queryTodoPort: QueryTodoPort,
    private val queryCompleteTodoPort: QueryCompleteTodoPort,
    private val commandTodoPort: CommandTodoPort
) : ModifyTodoUseCase{

    override fun execute(id: UUID, todoId: UUID, dto: ModifyTodoDto) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val group = queryGroupPort.findByIdOrNull(id)
            ?: throw GroupNotFoundException()

        val todo = queryTodoPort.findByIdAndTodoType(todoId, TodoType.PRIVATE)
            ?: throw TodoNotFoundException()

        if (!queryPrivateTodoPort.existsByTodoAndUser(todo, user)) throw NotTodoOwnerException()

        if (queryCompleteTodoPort.existsByTodo(todo)) throw AlreadyCompleteTodoException()

        commandTodoPort.saveTodo(todo.updateContent(dto.content))
    }

}