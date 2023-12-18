package team.kin.forest.domain.group.adapter.output.persistence

import org.springframework.stereotype.Component
import team.kin.forest.domain.group.adapter.output.persistence.mapper.GroupMapper
import team.kin.forest.domain.group.adapter.output.persistence.repository.GroupRepository
import team.kin.forest.domain.group.application.port.output.CommandGroupPort
import team.kin.forest.domain.group.domain.Group

@Component
class CommandGroupPersistenceAdapter(
    private val groupMapper: GroupMapper,
    private val groupRepository: GroupRepository
) : CommandGroupPort {
    override fun saveGroup(group: Group) {
        val groupEntity = groupMapper.toEntity(group)
        groupRepository.save(groupEntity)
    }
}