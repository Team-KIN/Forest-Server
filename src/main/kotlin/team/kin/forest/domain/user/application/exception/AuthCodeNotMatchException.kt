package team.kin.forest.domain.user.application.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class AuthCodeNotMatchException : ForestException(ErrorCode.AUTH_CODE_NOT_MATCH)