package org.kranj.vtrci.service


import org.kranj.vtrci.dtos.AuthenticationRequest
import org.kranj.vtrci.dtos.AuthenticationResponse
import org.kranj.vtrci.dtos.RegisterRequest
import org.kranj.vtrci.exception.RegisteredException
import org.kranj.vtrci.model.Organization
import org.kranj.vtrci.model.UserEntity
import org.kranj.vtrci.repository.OrganizationRepository
import org.kranj.vtrci.repository.UserRepository
import org.kranj.vtrci.security.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
    val organizationRepository: OrganizationRepository
) {
    fun register(registerRequest: RegisterRequest): AuthenticationResponse {

        if (userRepository.findByUsernameOrEmail(registerRequest.username, registerRequest.email) != null) {
            throw RegisteredException()
        }
        val organization = registerRequest.organization?.let { organizationRepository.getReferenceById(it) }
        val user = UserEntity(
            email = registerRequest.email,
            username = registerRequest.username,
            password = passwordEncoder.encode(registerRequest.password),
            role = registerRequest.role,
            organization = organization
        )

        userRepository.save(user)

        val token = jwtService.generateToken(user)

        return AuthenticationResponse(token)
    }

    fun authenticate(authenticationRequest: AuthenticationRequest): AuthenticationResponse {

        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                authenticationRequest.password
            )
        )

        val user = userRepository.findByUsername(authenticationRequest.username)

        return AuthenticationResponse(jwtService.generateToken(user!!))
    }

    fun getLoggedUser():UserEntity? {
        return if (SecurityContextHolder.getContext().authentication !== null) {
            val userDetails = SecurityContextHolder.getContext().authentication
                .principal as UserDetails
            val username = userDetails.username
            userRepository.findByUsername(username)
        } else null
    }

    fun getAllUsers():List<UserEntity> {
       return userRepository.findAll()
    }

    fun getOrgFromUser():Organization? {
        if (SecurityContextHolder.getContext().authentication !== null) {
            val userDetails = SecurityContextHolder.getContext().authentication
                .principal as UserDetails
            val username = userDetails.username
           val user = userRepository.findByUsername(username)
            val org = user?.organization
            return org
        } else return null
    }
}