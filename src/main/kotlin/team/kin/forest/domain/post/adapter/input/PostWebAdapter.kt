package team.kin.forest.domain.post.adapter.input

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.kin.forest.domain.post.adapter.input.data.QueryPostDetailsResponse
import team.kin.forest.domain.post.adapter.input.data.QueryPostsResponse
import team.kin.forest.domain.post.adapter.input.mapper.PostDataMapper
import team.kin.forest.domain.post.application.port.input.QueryPostDetailsUseCase
import team.kin.forest.domain.post.application.port.input.QueryPostsUseCase
import java.util.UUID

@RestController
@RequestMapping("/group/{id}/post")
class PostWebAdapter(
    private val queryPostsUseCase: QueryPostsUseCase,
    private val queryPostDetailsUseCase: QueryPostDetailsUseCase,
    private val postDataMapper: PostDataMapper
) {
    @GetMapping
    fun queryPosts(@PathVariable("id") groupId: UUID): ResponseEntity<QueryPostsResponse> =
        queryPostsUseCase.execute(groupId)
            .let { postDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{post_id}")
    fun queryPostDetails(@PathVariable("post_id") id: UUID, @PathVariable("id") groupId: UUID): ResponseEntity<QueryPostDetailsResponse> =
        queryPostDetailsUseCase.execute(id, groupId)
            .let { postDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }
}