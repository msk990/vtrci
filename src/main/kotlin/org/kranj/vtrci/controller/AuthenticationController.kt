package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.AuthenticationRequest
import org.kranj.vtrci.dtos.RegisterRequest
import org.kranj.vtrci.service.UserService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/auth")
class AuthenticationController(
    private val userService: UserService,
) {
    @CrossOrigin
    @GetMapping
    fun getAllUsers() = userService.getAllUsers()

    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequest) =
        ResponseEntity(userService.register(registerRequest), HttpStatus.CREATED)

    @CrossOrigin
    @PostMapping("/authenticate")
    fun authenticate(@RequestBody authenticationRequest: AuthenticationRequest) =
        ResponseEntity.ok(userService.authenticate(authenticationRequest))

    @CrossOrigin
    @GetMapping("/user")
    fun getLogged() = userService.getLoggedUser()
}