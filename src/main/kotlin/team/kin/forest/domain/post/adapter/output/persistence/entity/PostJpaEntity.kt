package team.kin.forest.domain.post.adapter.output.persistence.entity

import team.kin.forest.domain.group.adapter.output.persistence.entity.GroupJpaEntity
import team.kin.forest.domain.post.adapter.output.persistence.enums.PostTag
import team.kin.forest.domain.user.adapter.output.persistence.entity.UserJpaEntity
import team.kin.forest.common.entity.BaseUUIDEntity
import java.util.UUID
import javax.persistence.*

@Entity
class PostJpaEntity (
    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    var title: String,

    @Column(columnDefinition = "VARCHAR(300)", nullable = false)
    var content: String,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    var postTag: PostTag,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val group: GroupJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val user: UserJpaEntity
) : BaseUUIDEntity(id)