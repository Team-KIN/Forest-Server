package team.kin.forest.global.security.token.common.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class ExpiredRefreshTokenException : ForestException(ErrorCode.EXPIRED_REFRESH_TOKEN)