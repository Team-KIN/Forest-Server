package team.kin.forest.domain.todo.application.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.exception.NotGroupManagerException
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.todo.adapter.output.persistence.enums.TodoType
import team.kin.forest.domain.todo.application.exception.AlreadyCompleteTodoException
import team.kin.forest.domain.todo.application.exception.TodoNotFoundException
import team.kin.forest.domain.todo.application.port.input.DeleteTodoUseCase
import team.kin.forest.domain.todo.application.port.output.CommandTodoPort
import team.kin.forest.domain.todo.application.port.output.QueryCompleteTodoPort
import team.kin.forest.domain.todo.application.port.output.QueryTodoPort
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class DeleteTodoService(
    private val queryUserPort: QueryUserPort,
    private val queryGroupPort: QueryGroupPort,
    private val queryTodoPort: QueryTodoPort,
    private val queryCompleteTodoPort: QueryCompleteTodoPort,
    private val commandTodoPort: CommandTodoPort
) : DeleteTodoUseCase {

    override fun execute(id: UUID, todoId: UUID) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val group = queryGroupPort.findByIdOrNull(id)
            ?: throw GroupNotFoundException()

        val todo = queryTodoPort.findByIdAndTodoType(todoId, TodoType.PUBLIC)
            ?: throw TodoNotFoundException()

        if (group.manager != user) throw NotGroupManagerException()

        if (queryCompleteTodoPort.existsByTodo(todo)) throw AlreadyCompleteTodoException()

        commandTodoPort.deleteTodo(todo)
    }

}