package org.kranj.vtrci.dtos

import org.kranj.vtrci.model.*
import java.util.*


data class NewFoodDto (
    val foodName: String,
    val foodNameSl: String,
    val gen: Boolean,
    val processing: String,
    val portionSize: Double,
    val macroNutrients: MacroNutrients,
    val microNutrients: MicroNutrients,
    val ingredients: MutableSet<IngredientDto>,
    val images: MutableSet<FoodImage>?,
    val stages: MutableSet<FoodStage>?,
    val art: Art?,
    val tag: Set<MealTags>?
        )

data class FoodDto (
    var id: UUID?,
    val foodName: String,
    val foodNameSl: String,
    val gen: Boolean,
    val processing: String,
    val portionSize: Double,
    val macroNutrients: MacroNutrients,
    val microNutrients: MicroNutrients,
    val ingredients: MutableSet<Ingredient>?,
    val images: MutableSet<FoodImage>?,
    val stages: MutableSet<FoodStage>?,
    val art: Art?,
    val tag: Set<MealTags>?
)

data class IngredientDto (
        val itemId: String,
        val quantity: Double
        )

data class MacroNutrients(
    val energyKj: Double,
    val energyKcal: Double,
    val protein: Double,
    val carbs: Double,
    val sugars: Double,
    val dietaryFibre: Double,
    val fat: Double,
    val saturated: Double,

        )

data class MicroNutrients (
    val ca: Double,
    val fe: Double,
    val mg: Double,
    val k: Double,
    val na: Double,
    val zn: Double,
    val carotenoide: Double,
    val retinol: Double,
    val thiamin: Double,
    val riboflavin: Double,
    val niacin: Double,
    val b6: Double,
    val b12: Double,
    val folate: Double,
    val vitaminC: Double,
    val vitaminD: Double,
    val vitaminE: Double,

    ) {

}