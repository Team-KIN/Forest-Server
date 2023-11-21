package team.kin.forest.domain.comment.persistence.entity

import team.kin.forest.domain.group.persistence.entity.Group
import team.kin.forest.domain.post.persistence.entity.Post
import team.kin.forest.domain.user.persistence.entity.User
import team.kin.forest.global.entity.BaseUUIDEntity
import java.util.UUID
import javax.persistence.*

@Entity
class Comment (
    override val id: UUID,

    @Column(columnDefinition = "BINARY(500)", nullable = false)
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val post: Post,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val user: User
) : BaseUUIDEntity(id)