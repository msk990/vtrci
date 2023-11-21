package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.RacunDelivery
import org.kranj.vtrci.dtos.RacunDto
import org.kranj.vtrci.model.Racun
import org.kranj.vtrci.service.RacunService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.util.*

@RequestMapping("api/v1/racuns/buyer")
@RestController
class RacunBuyerController(
    val service: RacunService
) {
    @CrossOrigin
    @GetMapping
    fun getAllProducts() = service.getMyRacuns()

    @CrossOrigin
    @GetMapping("/{id}")
    fun getItem(@PathVariable id: UUID) = service.getById(id)

    @CrossOrigin
    @GetMapping("status/{stat}")
    fun getByStatus(@PathVariable stat: String) = service.getMyRacunsByStatus(stat)

    @CrossOrigin
    @GetMapping("/racun-delivery")
    fun getByPeriodAndProducer(@RequestParam("status") status:String,
                               @RequestParam("producerId") producerId:String
    ) = service.getRacunsByStatusAndProducerId(status, UUID.fromString(producerId))

    @CrossOrigin
    @GetMapping("/deliveries")
    fun getAllRacunsBetween(
        @RequestParam("start") startDate: Instant,
        @RequestParam("end") endDate: Instant,
    ) = service.getMyBuyerRacunsBetween(startDate, endDate)


    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveOrder(@RequestBody order: RacunDto): Racun? = service.create(order)

    @CrossOrigin
    @PutMapping("/{racunId}")
    fun jujeto(@PathVariable racunId: UUID) = service.deliverRacun(racunId)
}