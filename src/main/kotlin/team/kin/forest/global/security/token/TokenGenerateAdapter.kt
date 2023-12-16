package team.kin.forest.global.security.token

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import team.kin.forest.domain.user.application.port.output.TokenGeneratePort
import team.kin.forest.domain.user.application.port.output.dto.TokenDto
import team.kin.forest.domain.user.domain.Authority
import team.kin.forest.global.security.token.common.JwtExpTimeProperties
import team.kin.forest.global.security.token.common.JwtProperties
import java.util.*

@Component
class TokenGenerateAdapter(
    private val jwtProperties: JwtProperties,
    private val jwtExpTimeProperties: JwtExpTimeProperties
): TokenGeneratePort {

    override fun generateToken(id: UUID, authority: Authority): TokenDto =
        TokenDto(
            accessToken = generateAccessToken(id, authority),
            refreshToken = generateRefreshToken(id),
            accessTokenExpiredAt = jwtExpTimeProperties.accessExpiration.toLong(),
            refreshTokenExpiredAt = jwtExpTimeProperties.refreshExpiration.toLong()
        )

    private fun generateAccessToken(id: UUID, authority: Authority): String =
        Jwts.builder()
            .signWith(jwtProperties.accessSecret, SignatureAlgorithm.HS256)
            .setSubject(id.toString())
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.ACCESS)
            .claim(JwtProperties.AUTHORITY, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.accessExpiration * 1000))
            .compact()

    private fun generateRefreshToken(id: UUID): String =
        Jwts.builder()
            .signWith(jwtProperties.refreshSecret, SignatureAlgorithm.HS256)
            .setSubject(id.toString())
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.REFRESH)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.refreshExpiration * 1000))
            .compact()

}