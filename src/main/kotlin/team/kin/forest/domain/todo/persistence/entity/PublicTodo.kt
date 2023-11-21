package team.kin.forest.domain.todo.persistence.entity

import team.kin.forest.domain.group.persistence.entity.Group
import team.kin.forest.global.entity.BaseUUIDEntity
import java.util.*
import javax.persistence.*

@Entity
class PublicTodo(
    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val group: Group
) : BaseUUIDEntity(id)