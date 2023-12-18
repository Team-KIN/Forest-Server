package team.kin.forest.domain.group.adapter.output.persistence.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.post.adapter.output.persistence.entity.PostJpaEntity
import java.util.UUID

interface MemberRepository  : CrudRepository<PostJpaEntity, Long> {
    @Query("SELECT COUNT(m) FROM MemberJpaEntity m WHERE m.group.id = :groupId")
    fun countByGroupId(groupId: UUID): Int
}