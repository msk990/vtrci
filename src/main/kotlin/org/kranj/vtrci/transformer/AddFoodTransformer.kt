package org.kranj.vtrci.transformer


import org.kranj.vtrci.dtos.NewFoodDto
import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.Ingredient
import org.kranj.vtrci.model.Item
import org.kranj.vtrci.repository.ItemsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component
import java.util.*

@Component
class AddFoodTransformer: Transformer<NewFoodDto, Food> {
    @Autowired
   lateinit var itemsRepository: ItemsRepository
    override fun transform(source: NewFoodDto): Food {

        return Food (
            id= UUID.randomUUID(),
            foodName = source.foodName,
            foodNameSl = source.foodNameSl,
            gen = source.gen,
            processing = source.processing,
            portionSize = source.portionSize,
            energyKj = source.macroNutrients.energyKj,
            energyKcal = source.macroNutrients.energyKcal,
            protein = source.macroNutrients.protein,
            carbs = source.macroNutrients.carbs,
            sugars = source.macroNutrients.sugars,
            dietaryFibre = source.macroNutrients.dietaryFibre,
            fat = source.macroNutrients.fat,
            saturated = source.macroNutrients.saturated,
            ca = source.microNutrients.ca,
            fe = source.microNutrients.fe,
            mg = source.microNutrients.mg,
            k = source.microNutrients.k,
            na = source.microNutrients.na,
            zn = source.microNutrients.zn,
            carotenoide = source.microNutrients.carotenoide,
            retinol = source.microNutrients.retinol,
            thiamin = source.microNutrients.thiamin,
            riboflavin = source.microNutrients.riboflavin,
            niacin = source.microNutrients.niacin,
            b6 = source.microNutrients.b6,
            b12 = source.microNutrients.b12,
            folate = source.microNutrients.folate,
            vitaminC = source.microNutrients.vitaminC,
            vitaminD = source.microNutrients.vitaminD,
            vitaminE = source.microNutrients.vitaminE,


            ingredients = source.ingredients.map{
                Ingredient(
                    UUID.randomUUID(),
                    itemsRepository.getReferenceById(UUID.fromString(it.itemId)),

                    it.quantity,
                    it.special,
                    mutableListOf()

                )
            }.toMutableSet(),
            images=source.images?.toMutableSet(),
            photos = source.photos?.toMutableSet(),
            stages=source.stages?.toMutableSet(),
            art=source.art,
            tag = source.tag?.toSet(),
            meals = listOf(),
            instances = listOf()


        )
    }

}