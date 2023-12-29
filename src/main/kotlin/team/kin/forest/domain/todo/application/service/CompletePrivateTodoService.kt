package team.kin.forest.domain.todo.application.service

import team.kin.forest.common.annotation.ServiceWithTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.todo.adapter.output.persistence.enums.TodoType
import team.kin.forest.domain.todo.application.exception.AlreadyCompleteTodoException
import team.kin.forest.domain.todo.application.exception.TodoNotFoundException
import team.kin.forest.domain.todo.application.port.input.CompletePrivateTodoUseCase
import team.kin.forest.domain.todo.application.port.output.*
import team.kin.forest.domain.todo.domain.CompleteTodo
import team.kin.forest.domain.todo.domain.PrivateTodo
import team.kin.forest.domain.todo.domain.Todo
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithTransaction
class CompletePrivateTodoService(
    private val queryGroupPort: QueryGroupPort,
    private val queryUserPort: QueryUserPort,
    private val queryTodoPort: QueryTodoPort,
    private val queryCompleteTodoPort: QueryCompleteTodoPort,
    private val queryPrivateTodoPort: QueryPrivateTodoPort,
    private val commandCompleteTodoPort: CommandCompleteTodoPort,
    private val commandTodoPort: CommandTodoPort,
    private val commandPrivateTodoPort: CommandPrivateTodoPort
) : CompletePrivateTodoUseCase{

    override fun execute(id: UUID, todoId: UUID) {
        val group = queryGroupPort.findByIdOrNull(id)
            ?: throw GroupNotFoundException()

        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()

        val todo = queryTodoPort.findByIdAndTodoType(todoId, TodoType.PRIVATE)
            ?: throw TodoNotFoundException()

        val privateTodo = queryPrivateTodoPort.findByTodo(todo)

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

        val savePrivateTodo = PrivateTodo(
            id = privateTodo.id,
            todoStatus = true,
            user = privateTodo.user,
            todo = privateTodo.todo
        )

        commandCompleteTodoPort.saveCompleteTodo(completeTodo)

        commandTodoPort.saveTodo(saveTodo)

        commandPrivateTodoPort.savePrivateTodo(savePrivateTodo)
    }

}