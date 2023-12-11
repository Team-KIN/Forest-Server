package team.kin.forest.domain.todo.persistence.entity

import team.kin.forest.domain.group.persistence.entity.GroupJpaEntity
import team.kin.forest.domain.todo.persistence.enums.TodoType
import team.kin.forest.global.entity.BaseUUIDEntity
import java.util.*
import javax.persistence.*

@Entity
class TodoJpaEntity(
    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var content: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "todo_type", columnDefinition = "VARCHAR(100)", nullable = false)
    val todoType: TodoType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val group: GroupJpaEntity
) : BaseUUIDEntity(id)