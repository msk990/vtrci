package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.NewOrderDto
import org.kranj.vtrci.model.OrderEntity
import org.kranj.vtrci.service.OrderService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/orders")
@RestController
class OrderEntityController(val service: OrderService){

    @CrossOrigin
    @GetMapping("/poochie")
    fun getByName(@RequestParam("term") term: String,

                  pageable: Pageable
    ) = service.getMyOrders(term, pageable)

    @CrossOrigin
    @GetMapping("/{id}")
    fun getItem(@PathVariable id: UUID) = service.getById(id)

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveOrder(@RequestBody order: NewOrderDto): OrderEntity = service.create(order)
}