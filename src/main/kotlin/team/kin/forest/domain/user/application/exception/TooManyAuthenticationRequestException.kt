package team.kin.forest.domain.user.application.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class TooManyAuthenticationRequestException : ForestException(ErrorCode.TOO_MANY_AUTHENTICATION_CODE_REQUEST)