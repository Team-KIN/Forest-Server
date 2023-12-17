package team.kin.forest.global.security.token.common

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("jwt.time")
class JwtExpTimeProperties(
    val accessExpiration: Int,
    val refreshExpiration: Int
)