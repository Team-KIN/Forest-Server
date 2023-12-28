package team.kin.forest.domain.todo.application.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class AlreadyCompleteTodoException : ForestException(ErrorCode.ALREADY_COMPLETE_TODO)