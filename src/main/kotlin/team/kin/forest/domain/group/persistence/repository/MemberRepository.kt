package team.kin.forest.domain.group.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.post.persistence.entity.PostJpaEntity

interface MemberRepository  : CrudRepository<PostJpaEntity, Long>