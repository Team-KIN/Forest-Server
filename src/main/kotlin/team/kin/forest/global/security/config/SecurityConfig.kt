package team.kin.forest.global.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import team.kin.forest.domain.user.application.port.output.TokenParsePort
import team.kin.forest.global.security.handler.CustomAccessDeniedHandler
import team.kin.forest.global.security.handler.CustomAuthenticationEntryPoint

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtParserAdapter: TokenParsePort
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .apply(FilterConfig(jwtParserAdapter))
        authorizeHttpRequests(http)
        exceptionHandling(http)
        return http.build()
    }

    private fun authorizeHttpRequests(http: HttpSecurity) {
        http.authorizeHttpRequests()
            .mvcMatchers("/auth/**").permitAll()

            .mvcMatchers(HttpMethod.GET, "/group").authenticated()
            .mvcMatchers(HttpMethod.GET, "/group/{id}").authenticated()
            .mvcMatchers(HttpMethod.POST, "/group/{id}").authenticated()
            .mvcMatchers(HttpMethod.POST, "/group/code").authenticated()
            .mvcMatchers(HttpMethod.POST, "/group").authenticated()

            .mvcMatchers(HttpMethod.GET, "/group/{id}/post").authenticated()
            .mvcMatchers(HttpMethod.POST, "/group/{id}/post").authenticated()
            .mvcMatchers(HttpMethod.GET, "/group/{id}/post/{post_id}").authenticated()
            .mvcMatchers(HttpMethod.PATCH, "/group/{id}/post/{post_id}").authenticated()
            .mvcMatchers(HttpMethod.DELETE, "/group/{id}/post/{post_id}").authenticated()

            .mvcMatchers(HttpMethod.POST, "/group/{id}/post/{post_id}/comment").authenticated()
            .mvcMatchers(HttpMethod.PATCH, "/group/{id}/post/{post_id}/comment/{comment_id}").authenticated()
            .mvcMatchers(HttpMethod.DELETE, "/group/{id}/post/{post_id}/comment/{comment_id}").authenticated()

            .mvcMatchers(HttpMethod.POST, "/group/{id}/public-todo").authenticated()
            .mvcMatchers(HttpMethod.POST, "/group/{id}/private-todo").authenticated()
            .mvcMatchers(HttpMethod.POST, "/group/{id}/todo/{todo_id}").authenticated()
            .mvcMatchers(HttpMethod.POST, "/group/{id}/private-todo/{todo_id}").authenticated()
            .mvcMatchers(HttpMethod.PATCH, "/group/{id}/todo/{todo_id}").authenticated()
            .mvcMatchers(HttpMethod.DELETE, "/group/{id}/todo/{todo_id}").authenticated()
            .mvcMatchers(HttpMethod.DELETE, "/group/{id}/private-todo/{todo_id}").authenticated()

            .mvcMatchers(HttpMethod.GET, "/group/{id}/setting").authenticated()
            .mvcMatchers(HttpMethod.PATCH, "/group/{id}/setting").authenticated()
            .mvcMatchers(HttpMethod.DELETE, "/group/{id}/setting/{user_id}").authenticated()
            .mvcMatchers(HttpMethod.DELETE, "/group/{id}/setting/expurgation").authenticated()
            .mvcMatchers(HttpMethod.DELETE, "/group/{id}/setting/withdraw").authenticated()

            .mvcMatchers(HttpMethod.GET, "/main").authenticated()

            .mvcMatchers(HttpMethod.GET, "/user").authenticated()
            .mvcMatchers(HttpMethod.PATCH, "/user").authenticated()
            .mvcMatchers(HttpMethod.PATCH, "/user/password").authenticated()
            .mvcMatchers(HttpMethod.PATCH, "/user/profile").authenticated()
    }

    private fun exceptionHandling(http: HttpSecurity) {
        http.exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .accessDeniedHandler(CustomAccessDeniedHandler())
    }

}