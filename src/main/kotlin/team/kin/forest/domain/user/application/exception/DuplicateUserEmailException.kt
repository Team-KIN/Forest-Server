package team.kin.forest.domain.user.application.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class DuplicateUserEmailException: ForestException(ErrorCode.DUPLICATE_USER_EMAIL)