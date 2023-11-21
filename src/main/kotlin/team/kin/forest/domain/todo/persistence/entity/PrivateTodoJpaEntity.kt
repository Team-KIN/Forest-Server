package team.kin.forest.domain.todo.persistence.entity

import team.kin.forest.domain.group.persistence.entity.GroupJpaEntity
import team.kin.forest.domain.todo.persistence.enums.TodoStatus
import team.kin.forest.domain.user.persistence.entity.User
import team.kin.forest.global.entity.BaseUUIDEntity
import java.util.*
import javax.persistence.*

@Entity
class PrivateTodoJpaEntity (
    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var content: String,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(5)", nullable = false)
    var todoStatus: TodoStatus,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val group: GroupJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val user: User
) : BaseUUIDEntity(id)