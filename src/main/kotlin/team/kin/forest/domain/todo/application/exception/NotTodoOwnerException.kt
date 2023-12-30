package team.kin.forest.domain.todo.application.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class NotTodoOwnerException : ForestException(ErrorCode.NOT_TODO_OWNER)