package team.kin.forest.domain.comment.application.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class ForbiddenCommentException : ForestException(ErrorCode.FORBIDDEN_COMMENT)