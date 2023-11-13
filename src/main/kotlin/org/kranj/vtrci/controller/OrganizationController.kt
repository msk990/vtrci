package org.kranj.vtrci.controller

import org.kranj.vtrci.model.Organization
import org.kranj.vtrci.service.OrganizationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*


@RequestMapping("api/v1/organizations")
@RestController
class OrganizationController (val service: OrganizationService) {

    @CrossOrigin
    @GetMapping
    fun getAllOrganizations() = service.getAll()

    @CrossOrigin
    @GetMapping("/{id}")
    fun getProducer(@PathVariable id: String) = service.getById(id)


    @CrossOrigin
    @GetMapping("/mine")
    fun getThisProducer() = service.getMy()

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveProducer(@RequestBody producer: Organization): Organization = service.create(producer)

    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProducer(@PathVariable id: UUID) = service.remove(id)

    @CrossOrigin
    @PutMapping("/{id}")
    fun updateItem(@PathVariable id: UUID, @RequestBody producer: Organization) = service.update(id, producer)
}