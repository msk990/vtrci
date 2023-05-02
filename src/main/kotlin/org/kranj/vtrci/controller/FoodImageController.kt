package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.NewImageDto
import org.kranj.vtrci.model.FoodImage
import org.kranj.vtrci.service.FoodImageService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RequestMapping("api/v1/food-images")
@RestController
class FoodImageController(
    val service: FoodImageService
) {

   @CrossOrigin
   @GetMapping
   fun getAllFoodImages() = service.getAll()

    @CrossOrigin
    @PostMapping
    fun createNewFoodImage(
        @RequestParam("file") multipartFile:MultipartFile,
        @RequestParam("foodId") foodId: UUID,
        @RequestParam("position") position:Int
    ) = service.create(NewImageDto(foodId, multipartFile, position) )

    @CrossOrigin
    @PutMapping
    fun updateFoodImage(
        @RequestParam("foodId") foodId: UUID,
        @RequestParam("position") position:Int
    ) = service.update(foodId, position)


}