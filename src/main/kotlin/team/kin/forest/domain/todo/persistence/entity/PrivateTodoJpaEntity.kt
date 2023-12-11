package team.kin.forest.domain.todo.persistence.entity

import team.kin.forest.domain.user.persistence.entity.UserJpaEntity
import team.kin.forest.global.entity.BaseLongIdEntity
import java.util.*
import javax.persistence.*

@Entity
class PrivateTodoJpaEntity (
    override val id: Long,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(5)", nullable = false)
    var todoStatus: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val user: UserJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val todo: TodoJpaEntity,
) : BaseLongIdEntity(id)