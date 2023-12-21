package team.kin.forest.domain.todo.adapter.input

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.kin.forest.domain.todo.adapter.input.data.request.CreateTodoRequest
import team.kin.forest.domain.todo.adapter.input.data.response.TodoListResponse
import team.kin.forest.domain.todo.adapter.input.mapper.TodoDataMapper
import team.kin.forest.domain.todo.application.port.input.QueryTodoListUseCase
import team.kin.forest.domain.todo.application.port.input.WritePrivateTodoUseCase
import team.kin.forest.domain.todo.application.port.input.WritePublicTodoUseCase
import java.util.UUID

@RestController
@RequestMapping("/group")
class TodoWebAdapter(
    private val writePrivateTodoUseCase: WritePrivateTodoUseCase,
    private val writePublicTodoUseCase: WritePublicTodoUseCase,
    private val queryTodoListUseCase: QueryTodoListUseCase,
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

}