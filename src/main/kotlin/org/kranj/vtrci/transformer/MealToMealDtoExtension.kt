package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.MealDto
import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.Meal
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset.UTC
import java.time.temporal.ChronoUnit


fun Meal.toMealDto() : MealDto {

    val ldt = LocalDateTime.ofInstant(this.start, UTC).hour
    var ttl = when(ldt){
        7 -> "breakfast"
        9 -> "morning snack"
        11 -> "lunch"
        14 -> "afternoon snack"
        else -> ldt.toString()
    }
    var endd: Instant = when(ldt){
        7 -> this.start.plus(2, ChronoUnit.HOURS)
        9 -> this.start.plus(3, ChronoUnit.HOURS)
        8 -> this.start.plus(3, ChronoUnit.HOURS)
        10 -> this.start.plus(3, ChronoUnit.HOURS)
        14 -> this.start.plus(2, ChronoUnit.HOURS)
        else -> this.start.plus(2, ChronoUnit.HOURS)
    }
    return MealDto(
        id = this.id,
        start = this.start,
        end = endd,
        title = ttl,
        foods = this.foods?.map(Food::toFoodForMeal),
        color = "#fff"

    )
}