package org.kranj.vtrci.transformer

import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.FoodInstance
import org.kranj.vtrci.model.IngredientInstance
import org.kranj.vtrci.repository.FoodRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*


@Component
class FoodInstanceTransformer : Transformer<Food, FoodInstance> {
@Autowired
lateinit var foodRepository: FoodRepository
    override fun transform(source: Food): FoodInstance {

        var ingredientInstances : MutableList<IngredientInstance> = mutableListOf()
        var ingredients = source.ingredients

        var foodInstance = FoodInstance(
            id = UUID.randomUUID(),
            food = source.id?.let { foodRepository.getReferenceById(it) },
            ingredientInstances = null,
            meal = null,

        )

        if (ingredients != null) {
            ingredients.forEach{ ing ->
                var ju = IngredientInstance(
                    id = null,
                    ingredient = ing,
                    eco = false,
                    local = false,
                    quality = false,
                    schema = false,
                    garden = false,
                    product = null

                )
                ju.foodInstance = foodInstance
                ingredientInstances.add(ju)
            }
        }
        foodInstance.ingredientInstances = ingredientInstances.toList()
        return foodInstance
    }
}
