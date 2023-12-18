package team.kin.forest.domain.todo.adapter.output.persistence.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.todo.adapter.output.persistence.entity.TodoJpaEntity
import java.util.*

interface TodoRepository : CrudRepository<TodoJpaEntity, UUID> {
    @Query("SELECT COUNT(t) FROM TodoJpaEntity t WHERE t.group.id = :groupId")
    fun countByGroupId(groupId: UUID): Int
}