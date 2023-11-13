package org.kranj.vtrci.controller.pub

import org.kranj.vtrci.service.MealPeriodService
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.util.*

@RequestMapping("api/v1/pub/meal-period")
@RestController
class MealPeriodControllerPub (val service: MealPeriodService) {

    @CrossOrigin
    @GetMapping("/poochie")
    fun getMealsBetween(@RequestParam("start") start: String,
                        @RequestParam("end") end: String,

    ) = service.getMealsInPeriod(Instant.parse(start), Instant.parse(end))

    @CrossOrigin
    @GetMapping("/juje")
    fun getMealsInDay(@RequestParam("event") event: String,


                        ) = service.getMealsInDay(Instant.parse(event))

    @CrossOrigin
    @GetMapping("/popchi")
    fun getAllMealsInDay(@RequestParam("event") event: String,


                      ) = service.getAllMealsInDay(Instant.parse(event))

    @CrossOrigin
    @GetMapping("/maimun")
    fun getNutrMealsDay(@RequestParam("event") event: String,


                         ) = service.getNutrMealsInDay(Instant.parse(event))

}