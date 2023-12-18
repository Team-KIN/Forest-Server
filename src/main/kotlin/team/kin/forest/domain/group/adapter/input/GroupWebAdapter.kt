package team.kin.forest.domain.group.adapter.input

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.kin.forest.domain.group.adapter.input.data.request.CreateGroupRequest
import team.kin.forest.domain.group.adapter.input.data.response.GroupCodeResponse
import team.kin.forest.domain.group.adapter.input.data.response.QueryGroupDetailsResponse
import team.kin.forest.domain.group.adapter.input.data.response.QueryGroupsResponse
import team.kin.forest.domain.group.adapter.input.mapper.GroupDataMapper
import team.kin.forest.domain.group.application.port.input.CreateGroupUseCase
import team.kin.forest.domain.group.application.port.input.QueryGroupDetailsUseCase
import team.kin.forest.domain.group.application.port.input.QueryGroupsUseCase
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/group")
class GroupWebAdapter(
    private val queryGroupsUseCase: QueryGroupsUseCase,
    private val queryGroupDetailsUseCase: QueryGroupDetailsUseCase,
    private val createGroupUseCase: CreateGroupUseCase,
    private val groupDataMapper: GroupDataMapper
) {

    @GetMapping
    fun queryGroups(): ResponseEntity<QueryGroupsResponse> =
        queryGroupsUseCase.execute()
            .let { groupDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{id}")
    fun queryGroupDetails(@PathVariable id: UUID): ResponseEntity<QueryGroupDetailsResponse> =
        queryGroupDetailsUseCase.execute(id)
            .let { groupDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun createGroup(@RequestBody @Valid createGroupRequest: CreateGroupRequest): ResponseEntity<GroupCodeResponse> =
        createGroupUseCase.execute(groupDataMapper toDto createGroupRequest)
            .let { groupDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }
}