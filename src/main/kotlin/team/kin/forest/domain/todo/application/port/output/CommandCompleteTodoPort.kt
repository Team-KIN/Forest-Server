package team.kin.forest.domain.todo.application.port.output

import team.kin.forest.domain.todo.domain.CompleteTodo

interface CommandCompleteTodoPort {

    fun saveCompleteTodo(completeTodo: CompleteTodo)

}