package team.kin.forest.domain.comment.adapter.input

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.kin.forest.common.annotation.MemberOnly
import team.kin.forest.domain.comment.adapter.input.data.request.CreateCommentRequest
import team.kin.forest.domain.comment.adapter.input.data.request.ModifyCommentRequest
import team.kin.forest.domain.comment.adapter.input.mapper.CommentDataMapper
import team.kin.forest.domain.comment.application.port.input.CreateCommentUseCase
import team.kin.forest.domain.comment.application.port.input.DeleteCommentUseCase
import team.kin.forest.domain.comment.application.port.input.ModifyCommentUseCase
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/group/{id}/post/{post_id}/comment")
class CommentWebAdapter(
    private val createCommentUseCase: CreateCommentUseCase,
    private val modifyCommentUseCase: ModifyCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val commentDataMapper: CommentDataMapper
) {
    @PostMapping
    @MemberOnly
    fun createComment(
        @PathVariable("id") groupId: UUID, @PathVariable("post_id") postId: UUID,
        @RequestBody @Valid createCommentRequest: CreateCommentRequest
    ): ResponseEntity<Void> =
        createCommentUseCase.execute(groupId, postId, commentDataMapper toDto  createCommentRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PatchMapping("/{comment_id}")
    @MemberOnly
    fun modifyComment(
        @PathVariable("comment_id") id: UUID, @PathVariable("id") groupId: UUID, @PathVariable("post_id") postId: UUID,
        @RequestBody @Valid modifyCommentRequest: ModifyCommentRequest
    ): ResponseEntity<Void> =
        modifyCommentUseCase.execute(id, groupId, postId, commentDataMapper toDto  modifyCommentRequest)
            .let { ResponseEntity.noContent().build() }

    @DeleteMapping("/{comment_id}")
    @MemberOnly
    fun deleteComment(@PathVariable("comment_id") id: UUID, @PathVariable("id") groupId: UUID, @PathVariable("post_id") postId: UUID): ResponseEntity<Void> =
        deleteCommentUseCase.execute(id, groupId, postId)
            .let { ResponseEntity.noContent().build() }
}