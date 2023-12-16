package team.kin.forest.global.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration
import team.kin.forest.global.security.token.common.JwtExpTimeProperties
import team.kin.forest.global.security.token.common.JwtProperties

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class,
        JwtExpTimeProperties::class
    ]
)
class PropertiesScanConfig