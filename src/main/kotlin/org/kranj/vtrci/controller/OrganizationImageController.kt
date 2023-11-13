package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.NewOrganizationImageDto
import org.kranj.vtrci.dtos.NewProductImageDto
import org.kranj.vtrci.service.OrganizationImageService
import org.kranj.vtrci.service.ProductImageService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RequestMapping("api/v1/organization-images")
@RestController
class OrganizationImageController(
    val service: OrganizationImageService
) {
    @CrossOrigin
    @GetMapping
    fun getAllFoodImages() = service.getAll()

    @CrossOrigin
    @PostMapping
    fun createNewFoodImage(
        @RequestParam("file") multipartFile: MultipartFile,

        @RequestParam("position") position:Int
    ) = service.create(NewOrganizationImageDto(multipartFile, position) )

    @CrossOrigin
    @PutMapping
    fun updateFoodImage(

        @RequestParam("position") position:Int
    ) = service.update(position)
}