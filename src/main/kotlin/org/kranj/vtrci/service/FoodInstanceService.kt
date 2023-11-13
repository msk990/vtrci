package org.kranj.vtrci.service

import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.FoodInstance
import org.kranj.vtrci.model.Ingredient
import org.kranj.vtrci.model.IngredientInstance
import org.kranj.vtrci.repository.FoodInstanceRepository
import org.kranj.vtrci.transformer.FoodInstanceTransformer
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*
@Service
class FoodInstanceService(
    val repository: FoodInstanceRepository,
    val foodInstanceTransformer: FoodInstanceTransformer
) {

    fun getById(id:UUID) : FoodInstance? = repository.findByIdOrNull(id)
    fun getByMealId(id:UUID) : List<FoodInstance>? = repository.findByMealId(id)

    fun create(food: Food) : FoodInstance {

        return repository.save(foodInstanceTransformer.transform(food))
    }

    fun update(id:UUID, ingredients: List<Ingredient>?){
        val oldInstance = repository.getReferenceById(id)
        var foodInstance = FoodInstance(
            id = UUID.randomUUID(),
            food = oldInstance.food,
            ingredientInstances = null,
            meal = oldInstance.meal,

            )

        var ingredientInstances = mutableListOf<IngredientInstance>()
        if (ingredients != null) {
           ingredients.forEach {
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
           }

        }
        foodInstance.ingredientInstances = ingredientInstances.toList()
        repository.save(foodInstance)
    }

    fun remove(id: UUID) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}