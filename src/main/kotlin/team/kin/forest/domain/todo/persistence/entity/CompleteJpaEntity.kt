package team.kin.forest.domain.todo.persistence.entity

import team.kin.forest.domain.user.persistence.entity.UserJpaEntity
import team.kin.forest.global.entity.BaseLongIdEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class CompleteJpaEntity (
    override val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val user: UserJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val todo: TodoJpaEntity
) : BaseLongIdEntity(id)