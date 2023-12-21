package team.kin.forest.domain.post.adapter.output.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.kin.forest.domain.post.adapter.output.persistence.mapper.PostMapper
import team.kin.forest.domain.post.adapter.output.persistence.repository.PostRepository
import team.kin.forest.domain.post.application.port.output.QueryPostPort
import team.kin.forest.domain.post.domain.Post
import java.util.*

@Component
class QueryPostPersistenceAdapter(
    private val postRepository: PostRepository,
    private val postMapper: PostMapper
) : QueryPostPort {
    override fun findByIdOrNull(id: UUID): Post? {
        val postEntity = postRepository.findByIdOrNull(id)
        return postEntity?.let { postMapper.toDomain(it) }
    }

    override fun findAllByGroupId(groupId: UUID): List<Post> {
        val postEntities = postRepository.findAllByGroupId(groupId)
        return postMapper.toDomain(postEntities)
    }
}