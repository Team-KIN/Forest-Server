package team.kin.forest.domain.user.adapter.output.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.persistence.mapper.AuthenticationMapper
import team.kin.forest.domain.user.adapter.output.persistence.repository.AuthenticationRepository
import team.kin.forest.domain.user.application.port.output.QueryAuthenticationPort
import team.kin.forest.domain.user.domain.Authentication

@Component
class QueryAuthenticationPersistenceAdapter(
    private val authenticationRepository: AuthenticationRepository,
    private val authenticationMapper: AuthenticationMapper
): QueryAuthenticationPort {

    override fun findByEmailOrNull(email: String): Authentication? {
        val authenticationEntity = authenticationRepository.findByIdOrNull(email)
        return authenticationMapper.toDomain(authenticationEntity)
    }

}