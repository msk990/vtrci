package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.NewImageDto
import org.kranj.vtrci.dtos.NewProductImageDto
import org.kranj.vtrci.service.ProductImageService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RequestMapping("api/v1/product-images")
@RestController
class ProductImageController(
    val service: ProductImageService
) {
    @CrossOrigin
    @GetMapping
    fun getAllFoodImages() = service.getAll()

    @CrossOrigin
    @PostMapping
    fun createNewFoodImage(
        @RequestParam("file") multipartFile: MultipartFile,
        @RequestParam("productId") productId: UUID,
        @RequestParam("position") position:Int
    ) = service.create(NewProductImageDto(productId, multipartFile, position) )

    @CrossOrigin
    @PutMapping
    fun updateFoodImage(
        @RequestParam("productId") productId: UUID,
        @RequestParam("position") position:Int
    ) = service.update(productId, position)
}