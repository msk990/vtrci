package org.kranj.vtrci.controller.pub

import org.kranj.vtrci.service.MealTagService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("api/v1/pub/meal-tags")
@RestController
class MealTagControllerPub (val service:MealTagService) {
    @CrossOrigin
    @GetMapping
    fun getAllMealTags() = service.getAll()
}