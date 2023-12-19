package team.kin.forest.domain.user.adapter.output.message.exception

import team.kin.forest.global.error.ErrorCode
import team.kin.forest.global.error.exception.ForestException

class MessageSendFailedException : ForestException(ErrorCode.MESSAGE_SEND_FAILED)