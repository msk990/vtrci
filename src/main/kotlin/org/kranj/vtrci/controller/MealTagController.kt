package org.kranj.vtrci.controller

import org.kranj.vtrci.service.MealTagService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("api/v1/meal-tags")
@RestController
class MealTagController (val service:MealTagService) {
    @CrossOrigin
    @GetMapping
    fun getAllMealTags() = service.getAll()
}