package team.kin.forest.domain.group.adapter.input

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.kin.forest.domain.group.adapter.input.data.response.QueryGroupResponse
import team.kin.forest.domain.group.adapter.input.mapper.GroupDataMapper
import team.kin.forest.domain.group.application.port.input.QueryGroupsUseCase

@RestController
@RequestMapping("/group")
class GroupWebAdapter(
    private val queryGroupsUseCase: QueryGroupsUseCase,
    private val groupDataMapper: GroupDataMapper
) {

    @GetMapping
    fun queryGroups(): ResponseEntity<List<QueryGroupResponse>> =
        queryGroupsUseCase.execute()
            .map { groupDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }
}