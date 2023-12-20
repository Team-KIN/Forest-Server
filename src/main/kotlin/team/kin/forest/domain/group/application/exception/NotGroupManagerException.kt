package team.kin.forest.domain.group.application.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class NotGroupManagerException : ForestException(ErrorCode.NOT_GROUP_MANAGER)