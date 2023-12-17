package team.kin.forest.domain.group.adapter.output

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.enums.GroupScope
import team.kin.forest.domain.group.adapter.output.persistence.mapper.GroupMapper
import team.kin.forest.domain.group.adapter.output.persistence.repository.GroupRepository
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.group.domain.Group

@Component
class QueryGroupPersistenceAdapter (
    private val groupRepository: GroupRepository,
    private val groupMapper: GroupMapper
) : QueryGroupPort {
    override fun findAllByGroupScope(groupScope: GroupScope): List<Group> {
        val groupEntities = groupRepository.findAllByGroupScope(groupScope)
        return groupMapper.toDomain(groupEntities)
    }

}