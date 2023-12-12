package team.kin.forest.domain.todo.adapter.output.persistence.entity

import team.kin.forest.domain.group.persistence.entity.GroupJpaEntity
import team.kin.forest.common.entity.BaseUUIDEntity
import java.util.*
import javax.persistence.*

@Entity
class PublicTodoJpaEntity(
    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val group: GroupJpaEntity
) : BaseUUIDEntity(id)