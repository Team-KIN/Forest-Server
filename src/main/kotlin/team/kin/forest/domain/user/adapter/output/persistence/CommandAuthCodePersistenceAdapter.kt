package team.kin.forest.domain.user.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.persistence.mapper.AuthCodeMapper
import team.kin.forest.domain.user.adapter.output.persistence.repository.AuthCodeRepository
import team.kin.forest.domain.user.application.port.output.CommandAuthCodePort
import team.kin.forest.domain.user.domain.AuthCode

@Component
class CommandAuthCodePersistenceAdapter(
    private val authCodeRepository: AuthCodeRepository,
    private val authCodeMapper: AuthCodeMapper
): CommandAuthCodePort {

    override fun saveAuthCode(authCode: AuthCode): Int {
        val authCodeEntity = authCodeMapper.toEntity(authCode)
        return authCodeRepository.save(authCodeEntity).authCode
    }

}