package team.kin.forest.domain.user.adapter.input

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.kin.forest.domain.user.adapter.input.data.response.UserResponse
import team.kin.forest.domain.user.adapter.input.mapper.UserDataMapper
import team.kin.forest.domain.user.application.port.input.QueryMyPageUseCase

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val queryMyPageUseCase: QueryMyPageUseCase,
    private val userDataMapper: UserDataMapper
) {
    @GetMapping
    fun queryMyPage(): ResponseEntity<UserResponse> =
        queryMyPageUseCase.execute()
            .let { userDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }
}