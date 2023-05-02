package org.kranj.vtrci.dtos

import org.springframework.web.multipart.MultipartFile

data class NewFrontArtDto(
    val name: String,
    val file: MultipartFile
)
