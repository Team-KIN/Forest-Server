package team.kin.forest.domain.todo.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.todo.adapter.output.persistence.entity.PrivateTodoJpaEntity

interface PrivateTodoRepository : CrudRepository<PrivateTodoJpaEntity, Long>