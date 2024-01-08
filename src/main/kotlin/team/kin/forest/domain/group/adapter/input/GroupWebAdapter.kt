package team.kin.forest.domain.group.adapter.input

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.kin.forest.common.annotation.MemberOnly
import team.kin.forest.domain.group.adapter.input.data.request.CreateGroupRequest
import team.kin.forest.domain.group.adapter.input.data.request.GroupCodeRequest
import team.kin.forest.domain.group.adapter.input.data.response.GroupCodeResponse
import team.kin.forest.domain.group.adapter.input.data.response.QueryGroupDetailsResponse
import team.kin.forest.domain.group.adapter.input.data.response.QueryPublicGroupDetailsResponse
import team.kin.forest.domain.group.adapter.input.data.response.QueryGroupsResponse
import team.kin.forest.domain.group.adapter.input.mapper.GroupDataMapper
import team.kin.forest.domain.group.application.port.input.*
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/group")
class GroupWebAdapter(
    private val queryGroupsUseCase: QueryGroupsUseCase,
    private val queryPublicGroupDetailsUseCase: QueryPublicGroupDetailsUseCase,
    private val createGroupUseCase: CreateGroupUseCase,
    private val joinGroupUseCase: JoinGroupUseCase,
    private val joinGroupByCodeUseCase: JoinGroupByCodeUseCase,
    private val queryGroupDetailsUseCase: QueryGroupDetailsUseCase,
    private val deleteGroupMemberUseCase: DeleteGroupMemberUseCase,
    private val groupDataMapper: GroupDataMapper,
) {

    @GetMapping
    fun queryGroups(): ResponseEntity<QueryGroupsResponse> =
        queryGroupsUseCase.execute()
            .let { groupDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{id}")
    fun queryPublicGroupDetails(@PathVariable id: UUID): ResponseEntity<QueryPublicGroupDetailsResponse> =
        queryPublicGroupDetailsUseCase.execute(id)
            .let { groupDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @PostMapping("/{id}")
    fun joinGroup(@PathVariable id: UUID): ResponseEntity<QueryPublicGroupDetailsResponse> =
        joinGroupUseCase.execute(id)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/code")
    fun joinGroup(@RequestBody @Valid groupCodeRequest: GroupCodeRequest): ResponseEntity<QueryPublicGroupDetailsResponse> =
        joinGroupByCodeUseCase.execute(groupDataMapper toDto groupCodeRequest)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping
    fun createGroup(@RequestBody @Valid createGroupRequest: CreateGroupRequest): ResponseEntity<GroupCodeResponse> =
        createGroupUseCase.execute(groupDataMapper toDto createGroupRequest)
            .let { groupDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @MemberOnly
    @GetMapping("/{id}/setting")
    fun queryGroupDetails(@PathVariable("id") groupId: UUID): ResponseEntity<QueryGroupDetailsResponse> =
        queryGroupDetailsUseCase.execute(groupId)
            .let { groupDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @MemberOnly
    @DeleteMapping("/{id}/setting/{user_id}")
    fun deleteGroupMember(@PathVariable("id") groupId: UUID, @PathVariable("user_id") userId: UUID): ResponseEntity<Void> =
        deleteGroupMemberUseCase.execute(groupId, userId)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }
}