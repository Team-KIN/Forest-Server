package team.kin.forest.domain.todo.application.port.output

import team.kin.forest.domain.todo.domain.PrivateTodo

interface CommandPrivateTodoPort {

    fun savePrivateTodo(privateTodo: PrivateTodo)

}