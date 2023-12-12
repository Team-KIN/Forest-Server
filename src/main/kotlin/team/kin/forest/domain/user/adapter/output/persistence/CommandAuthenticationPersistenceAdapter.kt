package team.kin.forest.domain.user.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.persistence.mapper.AuthenticationMapper
import team.kin.forest.domain.user.adapter.output.persistence.repository.AuthenticationRepository
import team.kin.forest.domain.user.application.port.output.CommandAuthenticationPort
import team.kin.forest.domain.user.domain.Authentication

@Component
class CommandAuthenticationPersistenceAdapter(
    private val authenticationRepository: AuthenticationRepository,
    private val authenticationMapper: AuthenticationMapper
): CommandAuthenticationPort {

    override fun deleteAuthentication(authentication: Authentication) {
        val authenticationEntity = authenticationMapper.toEntity(authentication)
        authenticationRepository.delete(authenticationEntity)
    }

}