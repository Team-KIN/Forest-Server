package team.kin.forest.domain.group.adapter.output.persistence.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.group.adapter.output.persistence.entity.MemberJpaEntity
import java.util.UUID

interface MemberRepository  : CrudRepository<MemberJpaEntity, Long> {
    @Query("SELECT COUNT(m) FROM MemberJpaEntity m WHERE m.group.id = :groupId")
    fun countByGroupId(groupId: UUID): Int

    @Query("SELECT m FROM MemberJpaEntity m WHERE m.user.id = :userId")
    fun findAllByUserId(userId: UUID): List<MemberJpaEntity>
}