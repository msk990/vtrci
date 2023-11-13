package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.DeliveryChangeDto
import org.kranj.vtrci.dtos.NewItemDto
import org.kranj.vtrci.model.Item
import org.kranj.vtrci.service.DeliveryService
import org.kranj.vtrci.service.OrderService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.util.*

@RequestMapping("api/v1/deliveries")
@RestController
class DeliveryController(val service: DeliveryService) {
    @CrossOrigin
    @GetMapping("/poochie")
    fun getAll() = service.getMyDeliveries()

    @CrossOrigin
    @GetMapping("/seller")
    fun getAllSellerBetween(
        @RequestParam("start") startDate: Instant,
        @RequestParam("end") endDate: Instant,
    ) = service.getMySellerDeliveriesBetween(startDate, endDate)

    @CrossOrigin
    @GetMapping("/buyer")
    fun getAllBuyerBetween(
        @RequestParam("start") startDate: Instant,
        @RequestParam("end") endDate: Instant,
    ) = service.getMyBuyerDeliveriesBetween(startDate, endDate)

    @CrossOrigin
    @GetMapping("/{id}")
    fun getItem(@PathVariable id: UUID) = service.getById(id)

    @CrossOrigin
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun saveItem(
        @RequestBody dlvChange: DeliveryChangeDto
    ) = service.changeStatus(dlvChange.id, dlvChange.status)

}