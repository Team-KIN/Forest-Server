package team.kin.forest.domain.user.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.persistence.repository.UserRepository
import team.kin.forest.domain.user.application.port.output.QueryUserPort

@Component
class QueryUserPersistenceAdapter(
    private val userRepository: UserRepository
) : QueryUserPort {

    override fun existsByEmail(email: String): Boolean =
        userRepository.existsByEmail(email)

}