package org.kranj.vtrci.dtos

import java.util.UUID

data class FoodArtDto(
    val artId: UUID,
    val foodId: UUID,
)