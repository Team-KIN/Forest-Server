package team.kin.forest.global.security.token

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import team.kin.forest.domain.user.application.port.output.TokenParsePort
import team.kin.forest.domain.user.domain.Authority
import team.kin.forest.global.security.principal.AccountDetailsService
import team.kin.forest.global.security.token.common.JwtProperties
import team.kin.forest.global.security.token.common.exception.InvalidTokenTypeException
import java.security.Key
import javax.servlet.http.HttpServletRequest

@Component
class TokenParseAdapter(
    private val accountDetailsService: AccountDetailsService,
    private val jwtProperties: JwtProperties
) : TokenParsePort {

    override fun parseAccessToken(request: HttpServletRequest): String? =
        request.getHeader(JwtProperties.TOKEN_HEADER)
            .let { it ?: return null }
            .let { if (it.startsWith(JwtProperties.TOKEN_PREFIX)) it.replace(JwtProperties.TOKEN_PREFIX, "") else null }

    override fun parseRefreshToken(refreshToken: String): String? =
        if (refreshToken.startsWith(JwtProperties.TOKEN_PREFIX)) refreshToken.replace(JwtProperties.TOKEN_PREFIX, "") else null

    override fun authentication(token: String): Authentication =
        getAuthority(getTokenBody(token, jwtProperties.accessSecret))
            .let { UsernamePasswordAuthenticationToken(it, "", it.authorities) }

    private fun getTokenBody(token: String, secret: Key): Claims =
        Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(token)
            .body

    private fun getAuthority(body: Claims): UserDetails =
        when (body.get(JwtProperties.AUTHORITY, String::class.java)) {
            Authority.ROLE_ACCOUNT.name -> accountDetailsService.loadUserByUsername(body.subject)
            else -> throw InvalidTokenTypeException()
        }

}