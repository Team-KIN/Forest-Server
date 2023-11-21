package team.kin.forest.domain.group.persistence.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.kin.forest.domain.post.persistence.entity.PostJpaEntity
import java.util.UUID

@Repository
interface PostReposity : CrudRepository<PostJpaEntity, UUID>