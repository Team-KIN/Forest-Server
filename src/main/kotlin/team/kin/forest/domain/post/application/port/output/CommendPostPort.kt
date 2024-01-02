package team.kin.forest.domain.post.application.port.output

import team.kin.forest.domain.post.domain.Post
import java.util.*

interface CommendPostPort {
    fun savePost(post: Post): UUID
    fun deletePost(post: Post)
}