package team.kin.forest.domain.todo.application.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.todo.adapter.output.persistence.enums.TodoType
import team.kin.forest.domain.todo.application.exception.AlreadyCompleteTodoException
import team.kin.forest.domain.todo.application.exception.TodoNotFoundException
import team.kin.forest.domain.todo.application.port.input.CompleteTodoUseCase
import team.kin.forest.domain.todo.application.port.output.CommandCompleteTodoPort
import team.kin.forest.domain.todo.application.port.output.CommandTodoPort
import team.kin.forest.domain.todo.application.port.output.QueryCompleteTodoPort
import team.kin.forest.domain.todo.application.port.output.QueryTodoPort
import team.kin.forest.domain.todo.domain.CompleteTodo
import team.kin.forest.domain.todo.domain.Todo
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class CompleteTodoService(
    private val queryGroupPort: QueryGroupPort,
    private val queryUserPort: QueryUserPort,
    private val queryTodoPort: QueryTodoPort,
    private val queryCompleteTodoPort: QueryCompleteTodoPort,
    private val commandCompleteTodoPort: CommandCompleteTodoPort,
    private val commandTodoPort: CommandTodoPort
) : CompleteTodoUseCase {

    override fun execute(id: UUID, todoId: UUID) {
        val group = queryGroupPort.findByIdOrNull(id)
            ?: throw GroupNotFoundException()

        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val todo = queryTodoPort.findByIdAndTodoType(todoId, TodoType.PUBLIC)
            ?: throw TodoNotFoundException()

        if (queryCompleteTodoPort.existsByTodo(todo)) {
            throw AlreadyCompleteTodoException()
        }

        val completeTodo = CompleteTodo(
            id = 0L,
            user = user,
            todo = todo
        )

        val saveTodo = Todo(
            id = todo.id,
            content = todo.content,
            todoStatus = true,
            todoType = todo.todoType,
            group = todo.group
        )

        commandCompleteTodoPort.saveCompleteTodo(completeTodo)

        commandTodoPort.saveTodo(saveTodo)
    }

}