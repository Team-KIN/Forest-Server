package team.kin.forest.domain.user.application.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class AuthenticationNotFoundException: ForestException(ErrorCode.AUTHENTICATION_NOT_FOUND)