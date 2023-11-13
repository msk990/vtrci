package org.kranj.vtrci.dtos

import org.springframework.web.multipart.MultipartFile
import java.util.*

data class NewProcessStepDto(
    val productId: UUID,
    // val name:String,
    val file: MultipartFile,
    val position: Int
)
