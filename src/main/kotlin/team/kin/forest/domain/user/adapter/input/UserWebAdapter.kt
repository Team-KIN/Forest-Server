package team.kin.forest.domain.user.adapter.input

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.kin.forest.domain.user.adapter.input.data.request.ModifyUserNameRequest
import team.kin.forest.domain.user.adapter.input.data.request.ModifyUserPasswordRequest
import team.kin.forest.domain.user.adapter.input.data.request.ModifyUserProfileUrlRequest
import team.kin.forest.domain.user.adapter.input.data.response.UserResponse
import team.kin.forest.domain.user.adapter.input.mapper.UserDataMapper
import team.kin.forest.domain.user.application.port.input.ModifyUserNameUseCase
import team.kin.forest.domain.user.application.port.input.ModifyUserPasswordUseCase
import team.kin.forest.domain.user.application.port.input.ModifyUserProfileUrlUseCase
import team.kin.forest.domain.user.application.port.input.QueryMyPageUseCase
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val queryMyPageUseCase: QueryMyPageUseCase,
    private val modifyUserNameUseCase: ModifyUserNameUseCase,
    private val modifyUserProfileUrlUseCase: ModifyUserProfileUrlUseCase,
    private val modifyUserPasswordUseCase: ModifyUserPasswordUseCase,
    private val userDataMapper: UserDataMapper
) {
    @GetMapping
    fun queryMyPage(): ResponseEntity<UserResponse> =
        queryMyPageUseCase.execute()
            .let { userDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun modifyName(@RequestBody @Valid modifyUserNameRequest: ModifyUserNameRequest): ResponseEntity<Void> =
        modifyUserNameUseCase.execute(userDataMapper toDto modifyUserNameRequest)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @PatchMapping("/profile")
    fun modifyProfileUrl(@RequestBody @Valid modifyUserProfileUrlRequest: ModifyUserProfileUrlRequest): ResponseEntity<Void> =
        modifyUserProfileUrlUseCase.execute(userDataMapper toDto modifyUserProfileUrlRequest)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @PatchMapping("/password")
    fun modifyPassword(@RequestBody @Valid modifyUserPasswordRequest: ModifyUserPasswordRequest): ResponseEntity<Void> =
        modifyUserPasswordUseCase.execute(userDataMapper toDto modifyUserPasswordRequest)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

}