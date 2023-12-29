package team.kin.forest.domain.todo.adapter.output.persistence.entity

import team.kin.forest.common.entity.BaseIdEntity
import team.kin.forest.domain.user.adapter.output.persistence.entity.UserJpaEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class CompleteTodoJpaEntity (
    override val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val user: UserJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val todo: TodoJpaEntity
) : BaseIdEntity(id)