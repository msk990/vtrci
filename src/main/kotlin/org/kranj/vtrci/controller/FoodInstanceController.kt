package org.kranj.vtrci.controller

import org.kranj.vtrci.model.Ingredient
import org.kranj.vtrci.model.IngredientInstance
import org.kranj.vtrci.model.Meal
import org.kranj.vtrci.service.FoodInstanceService
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/food-instance")
@RestController
class FoodInstanceController(
    val service: FoodInstanceService
) {
    @CrossOrigin
    @GetMapping("/{id}")
    fun getFoodInstance(@PathVariable id: UUID) = service.getById(id)

    @CrossOrigin
    @GetMapping("bymeal/{id}")
    fun getFoodInstanceByMeal(@PathVariable id: UUID) = service.getByMealId(id)

    @CrossOrigin
    @PutMapping("/{id}")
    fun updateFoodInstance(@PathVariable id: UUID, @RequestBody ingredients: List<Ingredient>) = service.update(id, ingredients)

}