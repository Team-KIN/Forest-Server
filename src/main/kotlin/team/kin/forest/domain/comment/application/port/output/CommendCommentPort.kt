package team.kin.forest.domain.comment.application.port.output

import team.kin.forest.domain.comment.domain.Comment
import java.util.*

interface CommendCommentPort {
    fun saveComment(comment: Comment): UUID
}