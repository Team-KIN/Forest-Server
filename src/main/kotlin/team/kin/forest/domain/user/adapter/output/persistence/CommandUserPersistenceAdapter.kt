package team.kin.forest.domain.user.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.persistence.mapper.UserMapper
import team.kin.forest.domain.user.adapter.output.persistence.repository.UserRepository
import team.kin.forest.domain.user.application.port.output.CommandUserPort
import team.kin.forest.domain.user.domain.User
import java.util.*

@Component
class CommandUserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : CommandUserPort {

    override fun saveUser(user: User): UUID {
        val userEntity = userMapper.toEntity(user)
        return userRepository.save(userEntity).id
    }

}