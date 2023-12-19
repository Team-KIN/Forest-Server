package team.kin.forest.domain.user.adapter.input

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.kin.forest.domain.user.adapter.input.data.request.SignInRequest
import team.kin.forest.domain.user.adapter.input.data.request.SignUpRequest
import team.kin.forest.domain.user.adapter.input.data.response.TokenResponse
import team.kin.forest.domain.user.adapter.input.mapper.AuthDataMapper
import team.kin.forest.domain.user.application.port.input.*
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val authDataMapper: AuthDataMapper,
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val reissueTokenUseCase: ReissueTokenUseCase,
    private val sendAuthCodeUseCase: SendAuthCodeUseCase,
    private val verifyAuthCodeUseCase: VerifyAuthCodeUseCase,
    private val logoutUserUseCase: LogoutUserUseCase
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

    @DeleteMapping("/logout")
    fun logoutAccount(@RequestHeader refreshToken: String): ResponseEntity<Void> =
        logoutUserUseCase.execute(refreshToken)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @PostMapping("/send/phone-number/{phone_number}")
    fun sendAuthCode(@PathVariable("phone_number") phoneNumber: String): ResponseEntity<Void> =
        sendAuthCodeUseCase.execute(phoneNumber)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @GetMapping("/auth-code/{authCode}/phone-number/{phoneNumber}")
    fun verifyAuthCode(@PathVariable authCode: Int, @PathVariable phoneNumber: String): ResponseEntity<Void> =
        verifyAuthCodeUseCase.execute(authCode, phoneNumber)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

}