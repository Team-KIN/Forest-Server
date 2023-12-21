package team.kin.forest.domain.todo.application.service

import team.kin.forest.common.annotation.ServiceWithReadOnlyTransaction
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.exception.MemberNotFoundException
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.group.domain.Member
import team.kin.forest.domain.todo.adapter.output.persistence.enums.TodoType
import team.kin.forest.domain.todo.application.port.input.QueryTodoListUseCase
import team.kin.forest.domain.todo.application.port.input.dto.TodoListDto
import team.kin.forest.domain.todo.application.port.output.QueryCompletePort
import team.kin.forest.domain.todo.application.port.output.QueryPrivateTodoPort
import team.kin.forest.domain.todo.application.port.output.QueryTodoPort
import team.kin.forest.domain.todo.domain.Todo
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithReadOnlyTransaction
class QueryTodoListService(
    private val queryUserPort: QueryUserPort,
    private val queryGroupPort: QueryGroupPort,
    private val queryMemberPort: QueryMemberPort,
    private val queryTodoPort: QueryTodoPort,
    private val queryPrivateTodoPort: QueryPrivateTodoPort,
    private val queryCompletePort: QueryCompletePort,
) : QueryTodoListUseCase {

    override fun execute(groupId: UUID): TodoListDto {
        val user = queryUserPort.findCurrentUser()
            ?: throw UserNotFoundException()
        val group = queryGroupPort.findByIdOrNull(groupId)
            ?: throw GroupNotFoundException()
        val member = Member(
            user = user,
            group = group
        )

        if (!queryMemberPort.existsMember(member)) {
            throw MemberNotFoundException()
        }

        val todo = queryTodoPort.findAllByGroupAndTodoType(group, TodoType.PUBLIC)
        val privateTodo = queryPrivateTodoPort.findAllByUser(user)
        val totalMember = queryMemberPort.countByGroupId(groupId)

        val list = TodoListDto(
            publicTodos = todo.map {
                TodoListDto.PublicTodo(
                    id = it.id,
                    content = it.content,
                    achievementRate = ((queryCompletePort.countByUserAndTodo(user, it).toDouble() / totalMember.toDouble()) * 100).toInt(),
                    todoStatus = false
                )
            },
            privateTodos = privateTodo.map {
                TodoListDto.PrivateTodo(
                    id = it.todo.id,
                    content = it.todo.content,
                    todoStatus = it.todoStatus

                )
            }
        )

        return list

    }

}