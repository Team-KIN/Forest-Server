package team.kin.forest.domain.todo.adapter.output.persistence.entity

import team.kin.forest.common.entity.BaseIdEntity
import team.kin.forest.domain.user.adapter.output.persistence.entity.UserJpaEntity
import javax.persistence.*

@Entity
@Table(name = "private_todo")
class PrivateTodoJpaEntity (
    override val id: Long,

    @Column(columnDefinition = "VARCHAR(5)", nullable = false)
    var todoStatus: Boolean,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val todo: TodoJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val user: UserJpaEntity
) : BaseIdEntity(id)