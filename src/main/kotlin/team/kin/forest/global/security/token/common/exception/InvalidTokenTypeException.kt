package team.kin.forest.global.security.token.common.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class InvalidTokenTypeException: ForestException(ErrorCode.INVALID_TOKEN_TYPE)