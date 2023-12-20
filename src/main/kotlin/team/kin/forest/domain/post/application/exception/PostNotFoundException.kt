package team.kin.forest.domain.post.application.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class PostNotFoundException : ForestException(ErrorCode.POST_NOT_FOUND)