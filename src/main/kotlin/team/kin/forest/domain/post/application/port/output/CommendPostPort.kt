package team.kin.forest.domain.post.application.port.output

import team.kin.forest.domain.post.domain.Post

interface CommendPostPort {
    fun savePost(post: Post): Post
}