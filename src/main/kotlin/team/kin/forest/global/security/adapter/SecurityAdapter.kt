package team.kin.forest.global.security.adapter

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import team.kin.forest.domain.user.application.port.output.SecurityPort
import java.util.*

@Component
class SecurityAdapter : SecurityPort {
    override fun queryCurrentUserId(): UUID =
        UUID.fromString(SecurityContextHolder.getContext().authentication.name)
}