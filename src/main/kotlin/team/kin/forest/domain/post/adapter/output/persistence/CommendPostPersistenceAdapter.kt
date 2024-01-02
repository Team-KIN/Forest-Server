package team.kin.forest.domain.post.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.post.adapter.output.persistence.mapper.PostMapper
import team.kin.forest.domain.post.adapter.output.persistence.repository.PostRepository
import team.kin.forest.domain.post.application.port.output.CommendPostPort
import team.kin.forest.domain.post.domain.Post
import java.util.*

@Component
class CommendPostPersistenceAdapter(
    private val postRepository: PostRepository,
    private val postMapper: PostMapper
) : CommendPostPort {
    override fun savePost(post: Post): UUID {
        val postEntity = postMapper.toEntity(post)
        return postRepository.save(postEntity).id
    }

    override fun deletePost(post: Post) {
        val postEntity = postMapper.toEntity(post)
        return postRepository.delete(postEntity)
    }
}