package team.kin.forest.global.security.principal

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import team.kin.forest.common.annotation.ServiceWithReadOnlyTransaction
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import java.util.*

@ServiceWithReadOnlyTransaction
class AccountDetailsService(
    private val queryUserPort: QueryUserPort
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails =
        queryUserPort.findByIdOrNull(UUID.fromString(username))
            .let { it ?: throw UserNotFoundException() }
            .let { AccountDetails(it.id) }

}