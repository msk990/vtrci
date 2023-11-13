package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.DeliveryChangeDto
import org.kranj.vtrci.dtos.DeliveryConfirmationDto
import org.kranj.vtrci.service.DeliveryService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/confirm-delivery")
@RestController
class ConfirmDeliveryController (val service:DeliveryService) {

    @CrossOrigin
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun askDelivery(
        @PathVariable id: UUID
    ) = service.askToDeliver(id)

    @CrossOrigin
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    fun confirmDelivery(
        @RequestBody confirmation: DeliveryConfirmationDto
    ) = service.askConfirmDelivery(UUID.fromString(confirmation.deliveryId), UUID.fromString(confirmation.notifId))

}