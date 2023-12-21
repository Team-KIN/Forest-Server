package team.kin.forest.domain.todo.adapter.input

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.kin.forest.domain.todo.application.port.input.WritePrivateTodoUseCase
import team.kin.forest.domain.todo.application.port.input.WritePublicTodoUseCase
import java.util.UUID

@RestController
@RequestMapping("/group")
class TodoWebAdapter(
    private val writePrivateTodoUseCase: WritePrivateTodoUseCase,
    private val writePublicTodoUseCase: WritePublicTodoUseCase
) {

    @PostMapping("/{id}/private-todo")
    fun writePrivateTodo(@PathVariable id: UUID, @RequestBody content: String): ResponseEntity<Void> =
        writePrivateTodoUseCase.execute(id, content)
            .run { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/{id}/public-todo")
    fun writePublicTodo(@PathVariable id: UUID, @RequestBody content: String): ResponseEntity<Void> =
        writePublicTodoUseCase.execute(id, content)
            .run { ResponseEntity.status(HttpStatus.CREATED).build() }

}