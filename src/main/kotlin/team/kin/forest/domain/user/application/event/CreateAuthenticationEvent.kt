package team.kin.forest.domain.user.application.event

import team.kin.forest.domain.user.domain.Authentication

data class CreateAuthenticationEvent(
    val authentication: Authentication
)