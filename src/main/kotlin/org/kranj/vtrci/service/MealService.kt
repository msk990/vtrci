package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.ItemDto
import org.kranj.vtrci.dtos.MealForAdd
import org.kranj.vtrci.dtos.NewMealDto
import org.kranj.vtrci.model.FoodInstance
import org.kranj.vtrci.model.Item
import org.kranj.vtrci.model.Meal
import org.kranj.vtrci.model.MealSlot
import org.kranj.vtrci.repository.MealRepository
import org.kranj.vtrci.repository.MealSlotRepository
import org.kranj.vtrci.transformer.toItem
import org.kranj.vtrci.transformer.toItemDto
import org.kranj.vtrci.transformer.toMealDto
import org.kranj.vtrci.transformer.toMealForAdd
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.util.*

@Service
class MealService (
    val repository: MealRepository,
    val slotRepository: MealSlotRepository,
    val userService: UserService,
    val foodInstanceService: FoodInstanceService
) {

    fun getAll() = repository.findAll().map(Meal::toMealDto)

    fun getByStart(start:LocalDateTime) = repository.findByStart(start)

    fun getById(id: UUID): MealForAdd = repository.findByIdOrNull(id)?.toMealForAdd() ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(meal:NewMealDto):Meal {
        val organization = userService.getOrgFromUser()
//        return if (slotRepository.existsById(meal.mealSlot.position)) {
//            repository.save(meal)
//        } else {
        var ju: MealSlot
        if (slotRepository.existsById(meal.mealSlot.position)) {
            ju = slotRepository.getReferenceById(meal.mealSlot.position)
        }
        else{
           ju = slotRepository.save(meal.mealSlot)
        }

        var instances: MutableList<FoodInstance> = mutableListOf()
        if (meal.foods !=null) {
            meal.foods.forEach { food ->
                var instance = foodInstanceService.create(food)
              instances.add(instance)
            }

        }

            val newMeal = Meal(
                UUID.randomUUID(),
                meal.start,
                organization,
                meal.foods,
                instances.toList(),
                ju
            )
           return repository.save(newMeal)
      //  }
    }

    fun remove(id: UUID) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id:UUID, meal:Meal): Meal {
        if (repository.existsById(id)) {

            var instances: MutableList<FoodInstance> = mutableListOf()
            if (meal.foods !=null) {
                meal.foods.forEach { food ->
                    var instance = foodInstanceService.create(food)
                    instances.add(instance)
                }

            }
            val oldMeal = repository.getReferenceById(id)
            meal.id = id
            meal.organization = oldMeal.organization
            meal.mealSlot = oldMeal.mealSlot
            meal.foodInstances = instances
          return  repository.save(meal)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}