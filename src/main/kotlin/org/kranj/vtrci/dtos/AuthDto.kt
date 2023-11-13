package org.kranj.vtrci.dtos

import org.kranj.vtrci.model.Organization
import org.kranj.vtrci.model.RoleEnum
import java.util.UUID

data class RegisterRequest(
    val email: String,
    val username: String,
    val password: String,
    val role: RoleEnum,
    val organization: UUID?
)

data class AuthenticationRequest(
    val username: String,
    val password: String
)

data class AuthenticationResponse(
    val token: String
)

