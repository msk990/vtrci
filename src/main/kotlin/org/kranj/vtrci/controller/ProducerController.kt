package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.ItemDto
import org.kranj.vtrci.dtos.NewItemDto
import org.kranj.vtrci.model.Item
import org.kranj.vtrci.model.Producer
import org.kranj.vtrci.service.ProducerService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*


@RequestMapping("api/v1/producers")
@RestController
class ProducerController (val service: ProducerService) {

    @CrossOrigin
    @GetMapping
    fun getAllProducers() = service.getAll()

    @CrossOrigin
    @GetMapping("/{id}")
    fun getProducer(@PathVariable id: UUID) = service.getById(id)

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveProducer(@RequestBody producer: Producer): Producer = service.create(producer)

    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProducer(@PathVariable id: UUID) = service.remove(id)

    @CrossOrigin
    @PutMapping("/{id}")
    fun updateItem(@PathVariable id: UUID, @RequestBody producer: Producer) = service.update(id, producer)
}