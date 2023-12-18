package team.kin.forest.global.security.config

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import team.kin.forest.domain.user.application.port.output.TokenParsePort
import team.kin.forest.global.security.filter.ExceptionHandlerFilter
import team.kin.forest.global.security.filter.JwtRequestFilter

class FilterConfig(
    private val tokenParsePort: TokenParsePort
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(JwtRequestFilter(tokenParsePort), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ExceptionHandlerFilter(), JwtRequestFilter::class.java)
    }

}