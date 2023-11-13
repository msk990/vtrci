package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.NewItemDto
import org.kranj.vtrci.dtos.PackedDeliveryDto
import org.kranj.vtrci.model.Delivery
import org.kranj.vtrci.model.Item
import org.kranj.vtrci.service.PackService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("api/v1/packs")
@RestController
class PackController(val service: PackService)
{
    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveItem(@RequestBody pack: PackedDeliveryDto): Delivery = service.update(pack)

}