package team.kin.forest.domain.todo.application.port.output

import team.kin.forest.domain.todo.domain.Todo

interface CommandTodoPort {

    fun saveTodo(todo: Todo): Todo
    fun deleteTodo(todo: Todo)

}