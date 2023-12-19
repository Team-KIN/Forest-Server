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

    override fun findByPhoneNumberOrNull(phoneNumber: String): Authentication? {
        val authenticationEntity = authenticationRepository.findByIdOrNull(phoneNumber)
        return authenticationMapper.toDomain(authenticationEntity)
    }

    override fun existsByPhoneNumber(phoneNumber: String): Boolean {
        return authenticationRepository.existsById(phoneNumber)
    }

}