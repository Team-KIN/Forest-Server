package team.kin.forest.global.aop.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class NotGroupMemberException : ForestException(ErrorCode.NOT_GROUP_MEMBER)