package team.kin.forest.domain.todo.adapter.input

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.kin.forest.common.annotation.MemberOnly
import team.kin.forest.domain.todo.adapter.input.data.request.CreateTodoRequest
import team.kin.forest.domain.todo.adapter.input.data.request.ModifyTodoRequest
import team.kin.forest.domain.todo.adapter.input.data.response.TodoListResponse
import team.kin.forest.domain.todo.adapter.input.mapper.TodoDataMapper
import team.kin.forest.domain.todo.application.port.input.*
import java.util.UUID

@RestController
@RequestMapping("/group")
class TodoWebAdapter(
    private val writePrivateTodoUseCase: WritePrivateTodoUseCase,
    private val writePublicTodoUseCase: WritePublicTodoUseCase,
    private val queryTodoListUseCase: QueryTodoListUseCase,
    private val completeTodoUseCase: CompleteTodoUseCase,
    private val completePrivateTodoUseCase: CompletePrivateTodoUseCase,
    private val modifyTodoUseCase: ModifyTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val deletePrivateTodoUseCase: DeletePrivateTodoUseCase,
    private val todoDataMapper: TodoDataMapper
) {

    @PostMapping("/{id}/private-todo")
    fun writePrivateTodo(@PathVariable id: UUID, @RequestBody request: CreateTodoRequest): ResponseEntity<Void> =
        writePrivateTodoUseCase.execute(id, todoDataMapper toDto  request )
            .run { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/{id}/public-todo")
    fun writePublicTodo(@PathVariable id: UUID, @RequestBody request: CreateTodoRequest): ResponseEntity<Void> =
        writePublicTodoUseCase.execute(id, todoDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping("/{id}/todo-list")
    fun queryTodoList(@PathVariable id: UUID): ResponseEntity<TodoListResponse> =
        queryTodoListUseCase.execute(id)
            .let { todoDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @MemberOnly
    @PostMapping("/{id}/todo/{todo_id}")
    fun completeTodo(@PathVariable("id") groupId: UUID, @PathVariable("todo_id") todoId: UUID): ResponseEntity<Void> =
        completeTodoUseCase.execute(groupId, todoId)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @MemberOnly
    @PostMapping("/{id}/private-todo/{todo_id}")
    fun completePrivateTodo(@PathVariable("id") groupId: UUID, @PathVariable("todo_id") todoId: UUID): ResponseEntity<Void> =
        completePrivateTodoUseCase.execute(groupId, todoId)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @PatchMapping("/{id}/todo/{todo_id}")
    fun modifyTodo(
        @PathVariable("id") groupId: UUID,
        @PathVariable("todo_id") todoId: UUID,
        @RequestBody request: ModifyTodoRequest
    ): ResponseEntity<Void> =
        todoDataMapper.toDto(request)
            .let { modifyTodoUseCase.execute(groupId, todoId, it) }
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @DeleteMapping("/{id}/todo/{todo_id}")
    fun deleteTodo(@PathVariable("id") groupId: UUID, @PathVariable("todo_id") todoId: UUID): ResponseEntity<Void> =
        deleteTodoUseCase.execute(groupId, todoId)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @DeleteMapping("/{id}/private-todo/{todo_id}")
    fun deletePrivateTodo(@PathVariable("id") groupId: UUID, @PathVariable("todo_id") todoId: UUID): ResponseEntity<Void> =
        deletePrivateTodoUseCase.execute(groupId, todoId)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

}