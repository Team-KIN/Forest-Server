package team.kin.forest.domain.group.application.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class GroupNotFoundException : ForestException(ErrorCode.GROUP_NOT_FOUND)