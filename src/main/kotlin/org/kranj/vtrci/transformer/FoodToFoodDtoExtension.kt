package org.kranj.vtrci.transformer


import org.kranj.vtrci.dtos.*
import org.kranj.vtrci.model.Food
import org.kranj.vtrci.repository.FoodRepository
import org.kranj.vtrci.repository.IngredientsRepository
import org.kranj.vtrci.repository.ItemsRepository
import org.kranj.vtrci.service.IngredientsService
import org.springframework.beans.factory.annotation.Autowired


fun Food.toFoodDto() : FoodDto {

    var ingz : MutableSet<IngredientForFood> = mutableSetOf()

    this.ingredients?.forEach {
        val newIng = it.item?.let { it1 ->
            IngredientForFood(
                id = it.id,
                item = it1.toItemDto(),
                quantity = it.quantity,
                special = it.special
            )
        }
        if (newIng != null) {
            ingz.add(newIng)
        }
    }
    return FoodDto(
        id = this.id,
        foodName = this.foodName,
        foodNameSl = this.foodNameSl,
        gen = this.gen,
        processing = this.processing,
        portionSize = this.portionSize,
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
        ingredients = ingz,
        images = this?.images?.toMutableSet(),
        photos = this?.photos?.toMutableSet(),
        stages = this?.stages?.toMutableSet(),
        art = this?.art,
        tag = this.tag?.toSet(),
        meals = this.meals?.toSet()

    )

}

fun FoodReturnDto.toFood(
    foodRepository: FoodRepository,
    ingredientsService: IngredientsService,

): Food {

    val oldFood = this.id?.let { foodRepository.getReferenceById(it) }

    if (oldFood?.ingredients?.isNotEmpty() == true) {
        oldFood.ingredients.forEach{
            it.id?.let { it1 -> ingredientsService.remove(it1) }
        }
    }
    val newFood = Food(
        id = this.id,
        foodName = this.foodName,
        foodNameSl = this.foodNameSl,
        gen = this.gen,
        processing = this.processing,
        portionSize = this.portionSize,

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

        ingredients = this?.ingredients,
        photos = this?.photos?.toMutableSet(),
        images = this?.images?.toMutableSet(),
        stages = this?.stages?.toMutableSet(),
        art = this?.art,
        tag = this.tag?.toSet(),
        meals = oldFood?.meals,
        instances = oldFood?.instances
        )
    return newFood
}

fun Food.toFoodForMeal() : FoodForMeal {

    return FoodForMeal(
        id = this.id,
        foodName = this.foodName,
        foodNameSl = this.foodNameSl
    )


}