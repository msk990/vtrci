package org.kranj.vtrci.dtos

import org.springframework.web.multipart.MultipartFile

class NewCertificateDto(
    val certificateName: String,
    val file: MultipartFile
) {
}