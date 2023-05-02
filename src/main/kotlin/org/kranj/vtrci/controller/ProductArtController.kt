package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.FoodArtDto
import org.kranj.vtrci.dtos.ProductArtDto
import org.kranj.vtrci.service.FoodService
import org.kranj.vtrci.service.ProductService
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/product-art")
@RestController
class ProductArtController (val service: ProductService) {

    @CrossOrigin
    @PostMapping()
    fun juje(@RequestBody art: ProductArtDto) = service.addArt(art.artId, art.productId)

    @CrossOrigin
    @PutMapping("/{productId}")
    fun jujeto(@PathVariable productId: UUID) = service.removeArt(productId)
}