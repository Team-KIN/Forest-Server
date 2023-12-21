package team.kin.forest.domain.todo.adapter.output.persistence.entity

import team.kin.forest.domain.group.adapter.output.persistence.entity.GroupJpaEntity
import team.kin.forest.common.entity.BaseUUIDEntity
import team.kin.forest.domain.todo.adapter.output.persistence.enums.TodoType
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "todo")
class TodoJpaEntity(
    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var content: String,

    @Column(columnDefinition = "VARCHAR(5)", nullable = false)
    var todoStatus: Boolean,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    var todoType: TodoType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val group: GroupJpaEntity
) : BaseUUIDEntity(id)