package team.kin.forest.domain.comment.application.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class CommentNotFoundException : ForestException(ErrorCode.COMMENT_NOT_FOUND)