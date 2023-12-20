package team.kin.forest.domain.post.adapter.output.persistence.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.post.adapter.output.persistence.entity.PostJpaEntity
import java.util.*

interface PostRepository : CrudRepository<PostJpaEntity, UUID> {
    @Query("SELECT p FROM PostJpaEntity p WHERE p.group.id = :groupId")
    fun findAllByGroupId(groupId: UUID): List<PostJpaEntity>
}