package org.kranj.vtrci.controller

import org.kranj.vtrci.model.Identity
import org.kranj.vtrci.model.Producer
import org.kranj.vtrci.service.IdentityService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/identity")
@RestController
class IdentityController (val service: IdentityService) {

    @CrossOrigin
    @GetMapping
    fun getAllIdentities() = service.getAll()

    @CrossOrigin
    @GetMapping("/{id}")
    fun getIdentity(@PathVariable id: UUID) = service.getById(id)

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveIdentity(@RequestBody identity: Identity): Identity = service.create(identity)

    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteIdentity(@PathVariable id: UUID) = service.remove(id)

    @CrossOrigin
    @PutMapping("/{id}")
    fun updateItem(@PathVariable id: UUID, @RequestBody identity: Identity) = service.update(id, identity)

}