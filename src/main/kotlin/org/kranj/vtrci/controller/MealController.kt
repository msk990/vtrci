package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.FoodDto
import org.kranj.vtrci.dtos.NewFoodDto
import org.kranj.vtrci.dtos.NewMealDto
import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.Meal
import org.kranj.vtrci.service.MealService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/meals")
@RestController
class MealController (val service: MealService) {

    @CrossOrigin
    @GetMapping
    fun getAllMeals() = service.getAll()

    @CrossOrigin
    @GetMapping("/{id}")
    fun getMeal(@PathVariable id: UUID) = service.getById(id)

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveMeal(@RequestBody meal: NewMealDto) : Meal = service.create(meal)

    @CrossOrigin
    @PutMapping("/{id}")
    fun updateFood(@PathVariable id: UUID, @RequestBody meal: Meal) = service.update(id, meal)

    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteItem(@PathVariable id: UUID) = service.remove(id)

}