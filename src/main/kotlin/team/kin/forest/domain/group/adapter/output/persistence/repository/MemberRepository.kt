package team.kin.forest.domain.group.adapter.output.persistence.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.group.adapter.output.persistence.entity.GroupJpaEntity
import team.kin.forest.domain.group.adapter.output.persistence.entity.MemberJpaEntity
import team.kin.forest.domain.group.adapter.output.persistence.enums.MemberScope
import team.kin.forest.domain.user.adapter.output.persistence.entity.UserJpaEntity
import java.util.UUID

interface MemberRepository  : CrudRepository<MemberJpaEntity, Long> {
    @Query("SELECT COUNT(m) FROM MemberJpaEntity m WHERE m.group.id = :groupId")
    fun countByGroupId(groupId: UUID): Int

    @Query("SELECT m FROM MemberJpaEntity m WHERE m.user.id = :userId")
    fun findAllByUserId(userId: UUID): List<MemberJpaEntity>

    fun existsByUserAndGroup(user: UserJpaEntity, group: GroupJpaEntity): Boolean

    fun findAllByGroup(group: GroupJpaEntity): List<MemberJpaEntity>

    fun findByGroupAndUser(group: GroupJpaEntity, user: UserJpaEntity): MemberJpaEntity

    fun existsAllByGroupAndMemberScope(group: GroupJpaEntity, memberScope: MemberScope): Boolean
}