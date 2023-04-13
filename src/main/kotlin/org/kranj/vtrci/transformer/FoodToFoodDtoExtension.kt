package org.kranj.vtrci.transformer


import org.kranj.vtrci.dtos.FoodDto
import org.kranj.vtrci.dtos.FoodForMeal
import org.kranj.vtrci.dtos.MacroNutrients
import org.kranj.vtrci.dtos.MicroNutrients
import org.kranj.vtrci.model.Food




fun Food.toFoodDto() : FoodDto {

    return FoodDto(
        id = this.id,
        foodName = this.foodName,
        foodNameSl = this.foodNameSl,
        gen = this.gen,
        processing = this.processing,
        macroNutrients = MacroNutrients(
            energyKj = this.energyKj,
            energyKcal = this.energyKcal,
            protein = this.protein,
            carbs = this.carbs,
            sugars = this.sugars,
            dietaryFibre = this.dietaryFibre,
            fat = this.fat,
            saturated = this.saturated,

        ),
        microNutrients = MicroNutrients(
            ca = this.ca,
            fe = this.fe,
            mg = this.mg,
            k = this.k,
            na = this.na,
            zn = this.zn,
            carotenoide = this.carotenoide,
            retinol = this.retinol,
            thiamin = this.thiamin,
            riboflavin = this.riboflavin,
            niacin = this.niacin,
            b6 = this.b6,
            b12 = this.b12,
            folate = this.folate,
            vitaminC = this.vitaminC,
            vitaminD = this.vitaminD,
            vitaminE = this.vitaminE,
        ),
        ingredients = this?.ingredients?.toMutableSet(),
        tag = this.tag?.toSet()

    )

}

fun FoodDto.toFood(): Food {
    return Food(
        id = this.id,
        foodName = this.foodName,
        foodNameSl = this.foodNameSl,
        gen = this.gen,
        processing = this.processing,

        energyKj = this.macroNutrients.energyKj,
        energyKcal = this.macroNutrients.energyKcal,
        protein = this.macroNutrients.protein,
        carbs = this.macroNutrients.carbs,
        sugars = this.macroNutrients.sugars,
        dietaryFibre = this.macroNutrients.dietaryFibre,
        fat = this.macroNutrients.fat,
        saturated = this.macroNutrients.saturated,

        ca = this.microNutrients.ca,
        fe = this.microNutrients.fe,
        mg = this.microNutrients.mg,
        k = this.microNutrients.k,
        na = this.microNutrients.na,
        zn = this.microNutrients.zn,
        carotenoide = this.microNutrients.carotenoide,
        retinol = this.microNutrients.retinol,
        thiamin = this.microNutrients.thiamin,
        riboflavin = this.microNutrients.riboflavin,
        niacin = this.microNutrients.niacin,
        b6 = this.microNutrients.b6,
        b12 = this.microNutrients.b12,
        folate = this.microNutrients.folate,
        vitaminC = this.microNutrients.vitaminC,
        vitaminD = this.microNutrients.vitaminD,
        vitaminE = this.microNutrients.vitaminE,

        ingredients = this?.ingredients?.toMutableSet(),
        tag = this.tag?.toSet()
        )
}

fun Food.toFoodForMeal() : FoodForMeal {

    return FoodForMeal(
        id = this.id,
        foodName = this.foodName,
        foodNameSl = this.foodNameSl
    )


}