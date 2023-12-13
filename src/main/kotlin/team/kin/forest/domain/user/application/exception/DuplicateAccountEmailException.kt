package team.kin.forest.domain.user.application.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class DuplicateAccountEmailException: ForestException(ErrorCode.DUPLICATE_ACCOUNT_EMAIL)