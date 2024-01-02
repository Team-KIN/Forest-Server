package team.kin.forest.domain.post.adapter.input

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.kin.forest.common.annotation.MemberOnly
import team.kin.forest.domain.post.adapter.input.data.request.CreatePostRequest
import team.kin.forest.domain.post.adapter.input.data.request.ModifyPostRequest
import team.kin.forest.domain.post.adapter.input.data.response.QueryPostDetailsResponse
import team.kin.forest.domain.post.adapter.input.data.response.QueryPostsResponse
import team.kin.forest.domain.post.adapter.input.mapper.PostDataMapper
import team.kin.forest.domain.post.application.port.input.*
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/group/{id}/post")
class PostWebAdapter(
    private val queryPostsUseCase: QueryPostsUseCase,
    private val queryPostDetailsUseCase: QueryPostDetailsUseCase,
    private val createPostUseCase: CreatePostUseCase,
    private val modifyPostUseCase: ModifyPostUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val postDataMapper: PostDataMapper
) {
    @GetMapping
    @MemberOnly
    fun queryPosts(@PathVariable("id") groupId: UUID): ResponseEntity<QueryPostsResponse> =
        queryPostsUseCase.execute(groupId)
            .let { postDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{post_id}")
    @MemberOnly
    fun queryPostDetails(@PathVariable("post_id") id: UUID, @PathVariable("id") groupId: UUID): ResponseEntity<QueryPostDetailsResponse> =
        queryPostDetailsUseCase.execute(id, groupId)
            .let { postDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @PostMapping
    @MemberOnly
    fun createPost(@PathVariable("id") groupId: UUID, @RequestBody @Valid createPostRequest: CreatePostRequest): ResponseEntity<Void> =
        createPostUseCase.execute(groupId, postDataMapper toDto createPostRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PatchMapping("/{post_id}")
    @MemberOnly
    fun modifyPost(
        @PathVariable("post_id") id: UUID, @PathVariable("id") groupId: UUID,
        @RequestBody @Valid modifyPostRequest: ModifyPostRequest
    ): ResponseEntity<Void> =
        modifyPostUseCase.execute(id, groupId, postDataMapper toDto modifyPostRequest)
            .let { ResponseEntity.noContent().build() }

    @DeleteMapping("/{post_id}")
    @MemberOnly
    fun deletePost(@PathVariable("post_id") id: UUID, @PathVariable("id") groupId: UUID): ResponseEntity<Void> =
        deletePostUseCase.execute(id, groupId)
            .let { ResponseEntity.noContent().build() }
}