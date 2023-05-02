package org.kranj.vtrci.dtos

import org.springframework.web.multipart.MultipartFile

data class NewArtDto (
    val name:String,
    val src: MultipartFile,
    val simple:MultipartFile
        )