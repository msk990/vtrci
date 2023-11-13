package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.MealDto
import org.kranj.vtrci.dtos.MealForAdd
import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.Meal
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset.UTC
import java.time.temporal.ChronoUnit


fun Meal.toMealDto() : MealDto {

    val ldt = LocalDateTime.ofInstant(this.start, UTC).hour
    var ttl = when(ldt){
        7 -> "breakfast"
       // 9 -> "morning snack"
        10 -> "lunch"
        13 -> "afternoon snack"
        else -> ldt.toString()
    }
    var endd: Instant = when(ldt){
        7 -> this.start.plus(3, ChronoUnit.HOURS)
      //  9 -> this.start.plus(2, ChronoUnit.HOURS)
        10 -> this.start.plus(3, ChronoUnit.HOURS)
        13 -> this.start.plus(3, ChronoUnit.HOURS)
        else -> this.start.plus(3, ChronoUnit.HOURS)
    }
    return MealDto(
        id = this.id,
        start = this.start,
        end = endd,
        title = ttl,
        foods = this.foods?.map(Food::toFoodForMeal),
        color = "#fff",
        slot = this.mealSlot.position
//        position = this.mealSlot.position

    )
}

fun Meal.toMealForAdd() : MealForAdd {
    return MealForAdd(
       id =  this.id,
        start = this.start,
        foods = this.foods?.map(Food::toFoodDto)
    )
}