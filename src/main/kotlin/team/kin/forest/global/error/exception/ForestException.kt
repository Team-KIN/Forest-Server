package team.kin.forest.global.error.exception

import team.kin.forest.global.error.ErrorCode

open class ForestException(val errorCode: ErrorCode): RuntimeException(errorCode.message)