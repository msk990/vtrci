package org.kranj.vtrci.dtos

import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.MealSlot
import org.kranj.vtrci.model.Organization
import java.time.Instant
import java.util.UUID

data class FoodForMeal(
    val id: UUID?,
    val foodName:String,
    val foodNameSl: String

)

data class NewMealDto (
    val start:Instant,
    val foods: List<Food>?,
    val mealSlot: MealSlot
        )

data class UpdateMealDto(
    val id: UUID?,
    val start: Instant,
    val organization: Organization?,
    val foods: List<Food>?,
    val mealSlot: MealSlot
)

class MealDto(
    val id: UUID?,
    val start: Instant,
    val end: Instant,
    val title: String,
    val foods: List<FoodForMeal>?,
    val color: String,
    val slot: Int
   // val position: Int,
        ){


}
data class MealForAdd(
    val id:UUID?,
    val start:Instant,
    val foods: List<FoodDto>?,

)