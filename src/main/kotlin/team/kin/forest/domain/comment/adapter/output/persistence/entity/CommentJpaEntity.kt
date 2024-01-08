package team.kin.forest.domain.comment.adapter.output.persistence.entity

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import team.kin.forest.domain.post.adapter.output.persistence.entity.PostJpaEntity
import team.kin.forest.domain.user.adapter.output.persistence.entity.UserJpaEntity
import team.kin.forest.common.entity.BaseUUIDEntity
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "comment")
class CommentJpaEntity (
    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(150)", nullable = false)
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val post: PostJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val user: UserJpaEntity
) : BaseUUIDEntity(id)