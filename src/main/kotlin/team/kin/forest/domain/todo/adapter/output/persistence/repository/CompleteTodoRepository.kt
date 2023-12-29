package team.kin.forest.domain.todo.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.todo.adapter.output.persistence.entity.CompleteTodoJpaEntity
import team.kin.forest.domain.todo.adapter.output.persistence.entity.TodoJpaEntity
import team.kin.forest.domain.user.adapter.output.persistence.entity.UserJpaEntity

interface CompleteTodoRepository : CrudRepository<CompleteTodoJpaEntity, Long> {

    fun countByUserAndTodo(user: UserJpaEntity, todoJpaEntity: TodoJpaEntity?): Int
    fun existsByTodo(todoJpaEntity: TodoJpaEntity): Boolean

}