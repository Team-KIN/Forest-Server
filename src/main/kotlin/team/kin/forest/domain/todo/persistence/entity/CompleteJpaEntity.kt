package team.kin.forest.domain.todo.persistence.entity

import team.kin.forest.domain.user.persistence.entity.User
import team.kin.forest.global.entity.BaseUUIDEntity
import java.util.*
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class CompleteJpaEntity (
    override val id: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "public_todo_id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val publicTodo: PublicTodoJpaEntity
) : BaseUUIDEntity(id)