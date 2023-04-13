package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.ItemDto
import org.kranj.vtrci.dtos.NewItemDto
import org.kranj.vtrci.model.Item
import java.util.*

class UpdateItemTransformer: Transformer<ItemDto, Item> {

    override fun transform(source:ItemDto): Item {
        return Item (
            id= source.id,
            itemName = source.itemName,
            itemNameSl = source.itemNameSl,

            processing = source.processing,
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

            tag = source.tag?.toSet()
        )
    }
}