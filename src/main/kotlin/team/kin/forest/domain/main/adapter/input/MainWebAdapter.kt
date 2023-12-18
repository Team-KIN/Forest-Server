package team.kin.forest.domain.main.adapter.input

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.kin.forest.domain.main.adapter.input.data.response.QueryMainResponse
import team.kin.forest.domain.main.adapter.input.mapper.MainDataMapper
import team.kin.forest.domain.main.port.input.QueryMainUseCase

@RestController
@RequestMapping("/main")
class MainWebAdapter (
    private val queryMainUseCase: QueryMainUseCase,
    private val mainDataMapper: MainDataMapper
){
    @GetMapping
    fun queryMain(): ResponseEntity<QueryMainResponse> =
        queryMainUseCase.execute()
            .let { mainDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

}