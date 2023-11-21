package team.kin.forest.domain.post.persistence.entity

import team.kin.forest.domain.group.persistence.entity.Group
import team.kin.forest.domain.post.persistence.enums.PostTag
import team.kin.forest.domain.user.persistence.entity.User
import team.kin.forest.global.entity.BaseUUIDEntity
import java.util.UUID
import javax.persistence.*

@Entity
class Post (
    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var title: String,

    @Column(columnDefinition = "VARCHAR(1000)", nullable = false)
    var content: String,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    var postTag: PostTag,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val group: Group,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val user: User
) : BaseUUIDEntity(id)