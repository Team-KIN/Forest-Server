package team.kin.forest.domain.group.adapter.output.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.group.adapter.output.persistence.entity.GroupJpaEntity
import team.kin.forest.domain.post.adapter.output.persistence.entity.PostJpaEntity

interface MemberRepository  : CrudRepository<PostJpaEntity, Long> {
    fun countByGroup(group: GroupJpaEntity): Int
}