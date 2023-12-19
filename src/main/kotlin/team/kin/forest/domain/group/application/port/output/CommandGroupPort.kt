package team.kin.forest.domain.group.application.port.output

import team.kin.forest.domain.group.domain.Group

interface CommandGroupPort {
    fun saveGroup(group: Group): Group
}