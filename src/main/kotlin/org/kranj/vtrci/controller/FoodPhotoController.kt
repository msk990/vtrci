package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.NewImageDto
import org.kranj.vtrci.service.FoodPhotoService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RequestMapping("api/v1/food-photos")
@RestController
class FoodPhotoController(
    val service: FoodPhotoService
) {
    @CrossOrigin
    @PostMapping
    fun addNewFoodPhoto(
        @RequestParam("photoId") photoId: UUID,
        @RequestParam("foodId") foodId: UUID,
        @RequestParam("position") position:Int
    ) = service.addPhoto(foodId, photoId, position)

    @CrossOrigin
    @PutMapping
    fun updatePhotoImage(
        @RequestParam("foodId") foodId: UUID,
        @RequestParam("position") position:Int
    ) = service.update(foodId, position)

}