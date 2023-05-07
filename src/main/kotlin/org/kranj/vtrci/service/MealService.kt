package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.ItemDto
import org.kranj.vtrci.dtos.MealForAdd
import org.kranj.vtrci.model.Item
import org.kranj.vtrci.model.Meal
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
    val slotRepository: MealSlotRepository
) {

    fun getAll() = repository.findAll().map(Meal::toMealDto)

    fun getByStart(start:LocalDateTime) = repository.findByStart(start)

    fun getById(id: UUID): MealForAdd = repository.findByIdOrNull(id)?.toMealForAdd() ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(meal:Meal):Meal {
        return if (slotRepository.existsById(meal.mealSlot.position)) {
            repository.save(meal)
        } else {
            val ju = slotRepository.save(meal.mealSlot)

            val newMeal = Meal(
                UUID.randomUUID(),
                meal.start,
                meal.foods,
                ju
            )
            repository.save(newMeal)
        }
    }

    fun remove(id: UUID) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id:UUID, meal:Meal): Meal {
        return if (repository.existsById(id)) {
            meal.id = id
            repository.save(meal)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}