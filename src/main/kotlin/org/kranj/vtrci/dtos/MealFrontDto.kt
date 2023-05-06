package org.kranj.vtrci.dtos

import java.time.Instant
import java.util.*

data class FoodForFrontDto (
    val id: UUID,
    val foodName: String,

)
data class MealFrontDto(
    val id: UUID,
    val start: Instant,
    val foods: List<FoodForFrontDto>
)
