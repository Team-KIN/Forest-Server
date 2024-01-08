package team.kin.forest.domain.group.adapter.output.persistence.entity

import team.kin.forest.domain.group.adapter.output.persistence.enums.GroupScope
import team.kin.forest.domain.user.adapter.output.persistence.entity.UserJpaEntity
import team.kin.forest.common.entity.BaseUUIDEntity
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "group_entity")
class GroupJpaEntity (
    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(40)", nullable = false, updatable = false)
    val name: String,

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var content: String,

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var purpose: String,

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    var code: String,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)", nullable = false, updatable = false)
    val groupScope: GroupScope,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", columnDefinition = "BINARY(16)", nullable = false)
    var manager: UserJpaEntity
) : BaseUUIDEntity(id)