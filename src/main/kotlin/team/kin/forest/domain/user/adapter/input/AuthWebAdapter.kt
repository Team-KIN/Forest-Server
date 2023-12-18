package team.kin.forest.domain.user.adapter.input

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.kin.forest.domain.user.adapter.input.data.request.SignInRequest
import team.kin.forest.domain.user.adapter.input.data.request.SignUpRequest
import team.kin.forest.domain.user.adapter.input.data.response.TokenResponse
import team.kin.forest.domain.user.adapter.input.mapper.AuthDataMapper
import team.kin.forest.domain.user.application.port.input.ReissueTokenUseCase
import team.kin.forest.domain.user.application.port.input.SignInUseCase
import team.kin.forest.domain.user.application.port.input.SignUpUseCase
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val authDataMapper: AuthDataMapper,
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val reissueTokenUseCase: ReissueTokenUseCase
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid request: SignUpRequest): ResponseEntity<Void> =
        signUpUseCase.execute(authDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid request: SignInRequest): ResponseEntity<TokenResponse> =
        signInUseCase.execute(authDataMapper toDto request)
            .let { authDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/reissue")
    fun reissueToken(@RequestHeader refreshToken: String): ResponseEntity<TokenResponse> =
        reissueTokenUseCase.execute(refreshToken)
            .let { authDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

}