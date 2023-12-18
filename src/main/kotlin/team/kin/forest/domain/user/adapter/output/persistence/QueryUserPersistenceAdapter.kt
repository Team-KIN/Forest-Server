package team.kin.forest.domain.user.adapter.output.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.persistence.mapper.UserMapper
import team.kin.forest.domain.user.adapter.output.persistence.repository.UserRepository
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import team.kin.forest.domain.user.domain.User
import java.util.*

@Component
class QueryUserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : QueryUserPort {

    override fun existsByEmail(email: String): Boolean =
        userRepository.existsByEmail(email)

    override fun findByEmailOrNull(email: String): User? {
        val userEntity = userRepository.findByEmail(email)
        return userEntity?.let { userMapper.toDomain(it) }
    }

    override fun findByIdOrNull(id: UUID): User? {
        val userEntity = userRepository.findByIdOrNull(id)
        return userEntity?.let { userMapper.toDomain(it) }
    }

}