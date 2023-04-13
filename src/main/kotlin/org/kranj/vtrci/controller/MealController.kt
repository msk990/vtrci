package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.NewFoodDto
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
    fun saveMeal(@RequestBody meal: Meal) : Meal = service.create(meal)
}