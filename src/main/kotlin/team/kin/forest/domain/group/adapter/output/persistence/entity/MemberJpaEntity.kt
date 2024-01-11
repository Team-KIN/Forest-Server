package team.kin.forest.domain.group.adapter.output.persistence.entity

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import team.kin.forest.common.entity.BaseIdEntity
import team.kin.forest.domain.group.adapter.output.persistence.enums.MemberScope
import team.kin.forest.domain.user.adapter.output.persistence.entity.UserJpaEntity
import javax.persistence.*

@Entity
@Table(name = "member")
class MemberJpaEntity (
    override val id: Long,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    val memberScope: MemberScope,

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "group_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val group: GroupJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val user: UserJpaEntity
) : BaseIdEntity(id)