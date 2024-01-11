package team.kin.forest.global.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import team.kin.forest.domain.group.application.exception.GroupNotFoundException
import team.kin.forest.domain.group.application.port.output.QueryGroupPort
import team.kin.forest.domain.group.application.port.output.QueryMemberPort
import team.kin.forest.domain.group.domain.Member
import team.kin.forest.domain.user.application.exception.UserNotFoundException
import team.kin.forest.domain.user.application.port.output.QueryUserPort
import team.kin.forest.global.aop.exception.NotGroupMemberException
import java.util.*

@Aspect
@Component
class MemberOnlyAspect(
    private val queryUserPort: QueryUserPort,
    private val queryGroupPort: QueryGroupPort,
    private val queryMemberPort: QueryMemberPort
) {
    @Pointcut("@annotation(team.kin.forest.common.annotation.MemberOnly)")
    fun annotatedMethod() {}

    @Around("annotatedMethod()")
    fun isGroupMember(joinPoint: ProceedingJoinPoint): Any {

        val parameterNames = (joinPoint.signature as MethodSignature).parameterNames
        val methodArgs = joinPoint.args

        for ((index, paramName) in parameterNames.withIndex()) {
            if (paramName == "groupId") {
                val groupId = methodArgs[index] as UUID

                val user = queryUserPort.findCurrentUser()
                    ?: throw UserNotFoundException()

                val group = queryGroupPort.findByIdOrNull(groupId)
                    ?: throw GroupNotFoundException()

                val memberScope = queryMemberPort.findMemberScopeByGroupAndUser(group, user)

                val member = Member(
                    memberScope = memberScope,
                    user = user,
                    group = group
                )

                if (!queryMemberPort.existsMember(member))
                    throw NotGroupMemberException()

                return joinPoint.proceed()
            }
        }

        throw IllegalArgumentException("no groupId")
    }
}