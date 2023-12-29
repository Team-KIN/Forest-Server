package team.kin.forest.domain.todo.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.todo.adapter.output.persistence.entity.PrivateTodoJpaEntity
import team.kin.forest.domain.todo.adapter.output.persistence.entity.TodoJpaEntity
import team.kin.forest.domain.user.adapter.output.persistence.entity.UserJpaEntity

interface PrivateTodoRepository : CrudRepository<PrivateTodoJpaEntity, Long> {

    fun findAllByUser(user: UserJpaEntity): List<PrivateTodoJpaEntity>
    fun findByTodo(todo: TodoJpaEntity): PrivateTodoJpaEntity

}