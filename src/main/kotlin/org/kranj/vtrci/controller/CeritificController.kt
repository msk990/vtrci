package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.NewCertificateDto
import org.kranj.vtrci.service.CertificatesService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RequestMapping("api/v1/certificates")
@RestController
class CeritificController(
    val service: CertificatesService
) {
    @CrossOrigin
    @GetMapping
    fun getMyCetificates() = service.getMine()

    @CrossOrigin
    @PostMapping
    fun createCertificate(
        @RequestParam("file") multipartFile: MultipartFile,
        @RequestParam("name") name: String,
    ) = service.create(NewCertificateDto(name, multipartFile))

    @CrossOrigin
    @DeleteMapping
    fun deleteCertificate(
        @RequestParam("id") certId: String
    ) = service.delete(certId)
}