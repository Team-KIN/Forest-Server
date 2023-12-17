package team.kin.forest.domain.user.adapter.output.persistence.mapper

import org.springframework.stereotype.Component
import team.kin.forest.domain.user.adapter.output.persistence.entity.UserJpaEntity
import team.kin.forest.domain.user.domain.User

@Component
class UserMapper {

    fun toEntity(domain: User): UserJpaEntity =
        UserJpaEntity(
            id = domain.id,
            name = domain.name,
            email = domain.email,
            password = domain.password,
            profileUrl = domain.profileUrl
        )

    fun toDomain(entity: UserJpaEntity): User =
        User(
            id = entity.id,
            name = entity.name,
            email = entity.email,
            password = entity.password,
            profileUrl = entity.profileUrl
        )

}