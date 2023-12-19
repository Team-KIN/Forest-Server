package team.kin.forest.domain.group.adapter.output.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.enums.GroupScope
import team.kin.forest.domain.group.adapter.output.persistence.mapper.GroupMapper
import team.kin.forest.domain.group.adapter.output.persistence.repository.GroupRepository
import team.kin.forest.domain.group.application.port.input.dto.GroupCodeDto
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.group.domain.Group
import java.util.*

@Component
class QueryGroupPersistenceAdapter (
    private val groupRepository: GroupRepository,
    private val groupMapper: GroupMapper
) : QueryGroupPort {
    override fun findByIdOrNull(id: UUID): Group? {
        val groupEntity = groupRepository.findByIdOrNull(id)
        return groupEntity?.let { groupMapper.toDomain(it) }
    }

    override fun findAllByGroupScope(groupScope: GroupScope): List<Group> {
        val groupEntities = groupRepository.findAllByGroupScope(groupScope)
        return groupMapper.toDomain(groupEntities)
    }

    override fun findByCode(groupCodeDto: GroupCodeDto): Group? {
        val groupEntity = groupRepository.findByCode(groupCodeDto.code)
        return groupEntity?.let { groupMapper.toDomain(it) }
    }

}