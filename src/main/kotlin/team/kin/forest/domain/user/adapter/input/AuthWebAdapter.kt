package team.kin.forest.domain.user.adapter.input

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.kin.forest.domain.user.adapter.input.data.request.SignUpRequest
import team.kin.forest.domain.user.adapter.input.mapper.AuthDataMapper
import team.kin.forest.domain.user.application.port.input.SignUpUseCase
import javax.validation.Valid

@RestController
@RequestMapping("auth")
class AuthWebAdapter(
    private val authDataMapper: AuthDataMapper,
    private val signUpUseCase: SignUpUseCase
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid request: SignUpRequest): ResponseEntity<Void> =
        signUpUseCase.execute(authDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.CREATED).build() }

}