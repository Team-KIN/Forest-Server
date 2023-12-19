package team.kin.forest.domain.user.adapter.output.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.persistence.mapper.AuthCodeMapper
import team.kin.forest.domain.user.adapter.output.persistence.repository.AuthCodeRepository
import team.kin.forest.domain.user.application.port.output.QueryAuthCodePort
import team.kin.forest.domain.user.domain.AuthCode

@Component
class QueryAuthCodePersistenceAdapter(
    private val authCodeRepository: AuthCodeRepository,
    private val authCodeMapper: AuthCodeMapper
) : QueryAuthCodePort {

    override fun findByPhoneNumberOrNull(phoneNumber: String): AuthCode? {
        val authCodeEntity = authCodeRepository.findByIdOrNull(phoneNumber)
        return authCodeEntity?.let { authCodeMapper.toDomain(it) }
    }

}