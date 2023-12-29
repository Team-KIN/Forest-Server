package team.kin.forest.domain.todo.adapter.output.persistence.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.group.adapter.output.persistence.entity.GroupJpaEntity
import team.kin.forest.domain.todo.adapter.output.persistence.entity.TodoJpaEntity
import team.kin.forest.domain.todo.adapter.output.persistence.enums.TodoType
import java.util.*

interface TodoRepository : CrudRepository<TodoJpaEntity, UUID> {
    @Query("SELECT COUNT(t) FROM TodoJpaEntity t WHERE t.group.id = :groupId")
    fun countByGroupId(groupId: UUID): Int

    fun findAllByGroupAndTodoType(group: GroupJpaEntity, todoType: TodoType): List<TodoJpaEntity>

    fun findByIdAndTodoType(id: UUID, todoType: TodoType): TodoJpaEntity?
}