package team.kin.forest.domain.todo.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.todo.adapter.output.persistence.entity.CompleteJpaEntity
import team.kin.forest.domain.todo.adapter.output.persistence.entity.TodoJpaEntity
import team.kin.forest.domain.user.adapter.output.persistence.entity.UserJpaEntity

interface CompleteRepository : CrudRepository<CompleteJpaEntity, Long> {

    fun countByUserAndTodo(user: UserJpaEntity, todoJpaEntity: TodoJpaEntity?): Int

}