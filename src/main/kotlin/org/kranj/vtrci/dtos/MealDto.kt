package org.kranj.vtrci.dtos

import java.time.Instant
import java.util.UUID

data class FoodForMeal(
    val id: UUID?,
    val foodName:String,
    val foodNameSl: String

)
class MealDto (
    val id: UUID?,
    val start: Instant,
    val end: Instant,
    val title: String,
    val foods: List<FoodForMeal>?,
    val color: String
        ){


}