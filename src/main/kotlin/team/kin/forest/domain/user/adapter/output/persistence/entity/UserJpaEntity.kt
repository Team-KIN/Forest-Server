package team.kin.forest.domain.user.adapter.output.persistence.entity

import team.kin.forest.common.entity.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class UserJpaEntity(
    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(12)", nullable = false, updatable = false)
    val name: String,

    @Column(columnDefinition = "VARCHAR(300)", nullable = false)
    var email: String,

    @Column(columnDefinition = "VARCHAR(16)", nullable = false)
    var password: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    var profileUrl: String
) : BaseUUIDEntity(id)