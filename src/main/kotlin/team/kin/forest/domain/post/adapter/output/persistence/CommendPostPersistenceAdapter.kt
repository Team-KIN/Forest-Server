package team.kin.forest.domain.post.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.post.adapter.output.persistence.mapper.PostMapper
import team.kin.forest.domain.post.adapter.output.persistence.repository.PostRepository
import team.kin.forest.domain.post.application.port.output.CommendPostPort
import team.kin.forest.domain.post.domain.Post

@Component
class CommendPostPersistenceAdapter(
    private val postRepository: PostRepository,
    private val postMapper: PostMapper
) : CommendPostPort {
    override fun savePost(post: Post): Post {
        val postEntity = postRepository.save(postMapper.toEntity(post))
        return postMapper.toDomain(postEntity)
    }
}