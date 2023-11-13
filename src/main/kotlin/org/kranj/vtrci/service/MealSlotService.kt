package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.FoodDto
import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.MealSlot
import org.kranj.vtrci.repository.ArtRepository
import org.kranj.vtrci.repository.FrontArtRepository
import org.kranj.vtrci.repository.MealSlotRepository
import org.kranj.vtrci.transformer.toFoodDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*
@Service
class MealSlotService (val repository: MealSlotRepository,
                       val frontArtRepository: FrontArtRepository,) {
    fun getAll(): List<MealSlot> = repository.findAll()

    fun getById(id: Int): MealSlot = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun addArt(frontArtId: UUID, position: Int):MealSlot {

        val frontArt = frontArtRepository.getReferenceById(frontArtId)
       val meals = getById(position).meals
        val newSlot = MealSlot(
        position,
        frontArt,
            meals
          //  setOf()
        )
        return repository.save(newSlot)

    }
    fun deleteArt( position: Int):MealSlot {
        val mealSlot = repository.getReferenceById(position)

        val newSlot = MealSlot(
            position,
            null,
            mealSlot.meals

        )
        return repository.save(newSlot)

    }
}