package team.kin.forest.domain.group.application.port.output


import team.kin.forest.domain.group.domain.Member

interface CommandMemberPort {
    fun saveMember(member: Member)
    fun deleteMember(member: Member)
}