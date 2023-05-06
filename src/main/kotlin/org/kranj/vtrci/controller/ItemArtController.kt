package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.FoodArtDto
import org.kranj.vtrci.dtos.ItemArtDto
import org.kranj.vtrci.service.ItemsService
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/item-art")
@RestController
class ItemArtController (val service: ItemsService) {
    @CrossOrigin
    @PostMapping()
    fun juje(@RequestBody art: ItemArtDto) = service.addArt(art.artId, art.itemId)

    @CrossOrigin
    @PutMapping("/{itemId}")
    fun jujeto(@PathVariable itemId: UUID) = service.removeArt(itemId)
}