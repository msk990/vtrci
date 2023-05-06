package org.kranj.vtrci.service

import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.Meal
import org.kranj.vtrci.repository.MealRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

@Service
class MealPeriodService (val repository: MealRepository) {

    fun getMealsInPeriod(start: Instant, end:Instant) : List<Meal> = repository.findByStartGreaterThanAndStartLessThan(start, end)

    fun getMealsInDay(event:Instant) :AllMealInfo {
        //1. get start and end of day of meal
        val eventSpan = getDayPeriod(event)

        //      2.  get all meals of the day and filter out current meal
        val dayMeals = getMealsInPeriod(eventSpan.dayStart, eventSpan.dayEnd).filter{it -> it.start != event}
        //3. get all foods
       val foods:List<Food> = getFoods(dayMeals)
        //4. add all foods micros and macros
        val nutrients = addFoodNutrients(foods)
        //5. get sum of raw, home cooked, lightly processed, processed
        val processing = getProcessingSlice(foods)

        val allMealInfo = AllMealInfo(
            nutrients,
            processing
        )
        return allMealInfo

        //5. get sum of raw, home cooked, lightly processed, processed
    }

    fun getDayPeriod(event:Instant) : DayPeriod {
        var ldt: LocalDateTime = LocalDateTime.ofInstant(event, ZoneId.systemDefault())

        val ldtStart = ldt
            .withHour(0)
            .withMinute(0)
            .withSecond(0)
            .atZone(ZoneId.systemDefault())
            .toInstant()

       val ldtEnd = ldt
            .withHour(23)
            .withMinute(59)
            .withSecond(59)
            .atZone(ZoneId.systemDefault())
            .toInstant()

        val dayPeriod = DayPeriod(
            ldtStart,
            ldtEnd
        )
        return dayPeriod


    }

    fun getFoods(dayMeals:List<Meal>) : List<Food> {
        val foods:MutableList<Food> = mutableListOf()
        dayMeals.forEach{
                it -> it.foods?.forEach{food -> foods.add(food) }
        }
        return foods.toList()
    }

    fun addFoodNutrients(foods:List<Food>) : Nutrients {
        var totalNutrients: Nutrients = Nutrients(
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
        )
        foods.forEach{
            totalNutrients.energyKj = totalNutrients.energyKj + it.energyKj
            totalNutrients.energyKcal = totalNutrients.energyKcal + it.energyKcal
            totalNutrients.protein = totalNutrients.protein + it.protein
            totalNutrients.carbs = totalNutrients.carbs + it.carbs
            totalNutrients.sugars = totalNutrients.sugars + it.sugars
            totalNutrients.dietaryFibre = totalNutrients.dietaryFibre + it.dietaryFibre
            totalNutrients.fat = totalNutrients.fat + it.fat
            totalNutrients.saturated = totalNutrients.saturated + it.saturated
            totalNutrients.ca = totalNutrients.ca + it.ca
            totalNutrients.fe = totalNutrients.fe + it.fe
            totalNutrients.mg = totalNutrients.mg + it.mg
            totalNutrients.k = totalNutrients.k + it.k
            totalNutrients.na = totalNutrients.na + it.na
            totalNutrients.zn = totalNutrients.zn + it.zn
            totalNutrients.carotenoide = totalNutrients.carotenoide + it.carotenoide
            totalNutrients.retinol = totalNutrients.retinol + it.retinol
            totalNutrients.thiamin = totalNutrients.thiamin + it.thiamin
            totalNutrients.riboflavin = totalNutrients.riboflavin + it.riboflavin
            totalNutrients.niacin = totalNutrients.niacin + it.niacin
            totalNutrients.b6 = totalNutrients.b6 + it.b6
            totalNutrients.b12 = totalNutrients.b12 + it.b12
            totalNutrients.folate = totalNutrients.folate + it.folate
            totalNutrients.vitaminC = totalNutrients.vitaminC + it.vitaminC
            totalNutrients.vitaminD = totalNutrients.vitaminD + it.vitaminD
            totalNutrients.vitaminE = totalNutrients.vitaminE + it.vitaminE
        }
        return totalNutrients
    }
}
fun getProcessingSlice(foods: List<Food>): FoodProcessing {
    var totalProcessing:FoodProcessing  = FoodProcessing(
        0.0,
        0.0,
        0.0,
        0.0
    )
    foods.forEach{
        if (it.processing == "raw")
        {
            totalProcessing = FoodProcessing(
                totalProcessing.raw + it.portionSize,
                totalProcessing.light,
                totalProcessing.processed,
                totalProcessing.home
            )
        }
        else if (it.processing == "light")
        {
            totalProcessing = FoodProcessing(
                totalProcessing.raw,
                totalProcessing.light + it.portionSize,
                totalProcessing.processed,
                totalProcessing.home
            )
        }
        else if (it.processing == "processed")
        {
            totalProcessing = FoodProcessing(
                totalProcessing.raw,
                totalProcessing.light,
                totalProcessing.processed + it.portionSize,
                totalProcessing.home
            )
        }
        else if (it.processing == "home")
        {
            totalProcessing = FoodProcessing(
                totalProcessing.raw,
                totalProcessing.light,
                totalProcessing.processed,
                totalProcessing.home + it.portionSize
            )
        }


    }
    return totalProcessing
}
data class DayPeriod(val dayStart:Instant, val dayEnd:Instant)

data class Nutrients(
    var energyKj: Double,
    var energyKcal: Double,
    var protein: Double,
    var carbs: Double,
    var sugars: Double,
    var dietaryFibre: Double,
    var fat: Double,
    var saturated: Double,
    var ca: Double,
    var fe: Double,
    var mg: Double,
    var k: Double,
    var na: Double,
    var zn: Double,
    var carotenoide: Double,
    var retinol: Double,
    var thiamin: Double,
    var riboflavin: Double,
    var niacin: Double,
    var b6: Double,
    var b12: Double,
    var folate: Double,
    var vitaminC: Double,
    var vitaminD: Double,
    var vitaminE: Double,

    )

data class FoodProcessing (
    var raw: Double,
    var light: Double,
    var processed: Double,
    var home: Double,
)

data class AllMealInfo(
    val nutrients: Nutrients,
    val processing: FoodProcessing
)
