package team.kin.forest.domain.post.application.port.output.dto

import team.kin.forest.domain.post.adapter.output.persistence.enums.PostTag

data class CreatePostDto (
    val title: String,
    val content: String,
    val tag: PostTag
)