package team.kin.forest.domain.post.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.kin.forest.domain.post.persistence.entity.PostJpaEntity
import java.util.*

interface PostRepository : CrudRepository<PostJpaEntity, UUID>