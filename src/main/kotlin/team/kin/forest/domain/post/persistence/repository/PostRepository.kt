package team.kin.forest.domain.post.persistence.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.kin.forest.domain.post.persistence.entity.PostJpaEntity
import java.util.*

@Repository
interface PostRepository : CrudRepository<PostJpaEntity, UUID>