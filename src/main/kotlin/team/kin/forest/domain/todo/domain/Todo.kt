package team.kin.forest.domain.todo.domain

import team.kin.forest.common.annotation.RootAggregate
import team.kin.forest.domain.group.domain.Group
import team.kin.forest.domain.todo.adapter.output.persistence.enums.TodoType
import java.util.UUID

@RootAggregate
data class Todo(
    val id: UUID,
    var content: String,
    var todoStatus: Boolean,
    val todoType: TodoType,
    val group: Group
) {

    fun updateContent(newContent: String): Todo {
        this.content = newContent
        return this
    }

}