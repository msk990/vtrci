package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.NewProcessStepDto
import org.kranj.vtrci.dtos.NewProductImageDto
import org.kranj.vtrci.service.ProcessStepService
import org.kranj.vtrci.service.ProductImageService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RequestMapping("api/v1/process-steps")
@RestController
class ProcessStepController(
    val service: ProcessStepService
) {

    @CrossOrigin
    @GetMapping
    fun getAllProcessSteps() = service.getAll()

    @CrossOrigin
    @PostMapping
    fun createNewFoodImage(
        @RequestParam("file") multipartFile: MultipartFile,
        @RequestParam("productId") productId: UUID,
        @RequestParam("position") position:Int
    ) = service.create(NewProcessStepDto(productId, multipartFile, position) )

    @CrossOrigin
    @PutMapping
    fun updateFoodImage(
        @RequestParam("productId") productId: UUID,
        @RequestParam("position") position:Int
    ) = service.update(productId, position)
}