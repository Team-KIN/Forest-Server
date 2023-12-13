package team.kin.forest.global.security.adapter

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import team.kin.forest.domain.user.application.port.output.PasswordEncodePort

@Component
class PasswordEncodeAdapter(
    private val passwordEncoder: PasswordEncoder
): PasswordEncodePort {

    override fun passwordEncode(password: String): String =
        passwordEncoder.encode(password)

}