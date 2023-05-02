package org.kranj.vtrci.controller


import org.kranj.vtrci.dtos.FoodArtDto
import org.kranj.vtrci.service.FoodService
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/food-art")
@RestController
class FoodArtController (val service: FoodService) {

    @CrossOrigin
    @PostMapping()
    fun juje(@RequestBody art: FoodArtDto) = service.addArt(art.artId, art.foodId)

    @CrossOrigin
    @PutMapping("/{foodId}")
    fun jujeto(@PathVariable foodId:UUID) = service.removeArt(foodId)
}