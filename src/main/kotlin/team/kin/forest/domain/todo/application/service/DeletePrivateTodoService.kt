package team.kin.forest.domain.todo.application.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.todo.adapter.output.persistence.enums.TodoType
import team.kin.forest.domain.todo.application.exception.AlreadyCompleteTodoException
import team.kin.forest.domain.todo.application.exception.NotTodoOwnerException
import team.kin.forest.domain.todo.application.exception.TodoNotFoundException
import team.kin.forest.domain.todo.application.port.input.DeletePrivateTodoUseCase
import team.kin.forest.domain.todo.application.port.output.*
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class DeletePrivateTodoService(
    private val queryUserPort: QueryUserPort,
    private val queryGroupPort: QueryGroupPort,
    private val queryTodoPort: QueryTodoPort,
    private val queryPrivateTodoPort: QueryPrivateTodoPort,
    private val queryCompleteTodoPort: QueryCompleteTodoPort,
    private val commandTodoPort: CommandTodoPort,
    private val commandPrivateTodoPort: CommandPrivateTodoPort
) : DeletePrivateTodoUseCase{

    override fun execute(id: UUID, todoId: UUID) {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val group = queryGroupPort.findByIdOrNull(id)
            ?: throw GroupNotFoundException()

        val todo = queryTodoPort.findByIdAndTodoType(todoId, TodoType.PRIVATE)
            ?: throw TodoNotFoundException()

        val privateTodo = queryPrivateTodoPort.findByTodo(todo)

        if (privateTodo.user != user) throw NotTodoOwnerException()

        if (queryCompleteTodoPort.existsByTodo(todo)) throw AlreadyCompleteTodoException()

        commandPrivateTodoPort.deletePrivateTodo(privateTodo)

        commandTodoPort.deleteTodo(todo)
    }

}