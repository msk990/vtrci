package org.kranj.vtrci.controller

import org.kranj.vtrci.responses.UploadResponse
import org.kranj.vtrci.service.FileStorageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
class FileUploadController(private val fileStorageService: FileStorageService) {
    @PostMapping("/upload")
    fun uploadFile(
        @RequestParam(name = "file", required = false) file: MultipartFile?,
        @RequestParam("foodId") foodId: String?,
        @RequestParam("position") position: Int?
    ): ResponseEntity<UploadResponse> {
        val fileName = fileStorageService.storeFile(file!!)
        val uploadResponse = UploadResponse(fileName, foodId, position)
        return ResponseEntity.ok().body(uploadResponse)
    }
}