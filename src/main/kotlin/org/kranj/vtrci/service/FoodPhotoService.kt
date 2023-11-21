package org.kranj.vtrci.service

import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.FoodPhoto
import org.kranj.vtrci.repository.FoodPhotoRepository
import org.kranj.vtrci.repository.FoodRepository
import org.kranj.vtrci.repository.PhotoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class FoodPhotoService (
    val repository : FoodPhotoRepository,
    val foodRepository: FoodRepository,
    val photoRepository: PhotoRepository
        ){

    fun addPhoto(foodId: UUID, photoId:UUID, position: Int): ResponseEntity<Any>{
        val photo = photoRepository.getReferenceById(photoId)

        val foodPhoto = repository.save(
            FoodPhoto(
                UUID.randomUUID(),
                photo,
                position,
            )
        )

        val food = foodRepository.getReferenceById(foodId)

        var newPhotos: MutableSet<FoodPhoto> = mutableSetOf()

        if (food.photos?.isNotEmpty() == true){
            food.photos.forEach{
                if (it.position != position) {
                    newPhotos.add(it)
                }
            }
        }

        newPhotos.add(foodPhoto)

        val newFood = Food(
            food.id,
            food.foodName,
            food.foodNameSl,
            food.gen,
            food.processing,
            food.portionSize,
            food.energyKj,
            food.energyKcal,
            food.protein,
            food.carbs,
            food.sugars,
            food.dietaryFibre,
            food.fat,
            food.saturated,
            food.ca,
            food.fe,
            food.mg,
            food.k,
            food.na,
            food.zn,
            food.carotenoide,
            food.retinol,
            food.thiamin,
            food.riboflavin,
            food.niacin,
            food.b6,
            food.b12,
            food.folate,
            food.vitaminC,
            food.vitaminD,
            food.vitaminE,
            food.ingredients,
            food.images,
            newPhotos,
            food.art,
            food.stages,
            food.tag,
            food.meals,
            food.instances
        )

        return if (foodRepository.existsById(food.id!!)) {
          //  food.id = food.id
           // val savedFoodImage = foodRepository.save(newFood).images?.filter { it.position == img.position }

           foodRepository.save(newFood)
            ResponseEntity.status(HttpStatus.OK).body(foodPhoto)
//            ResponseEntity.status(HttpStatus.OK).body(foodPhoto)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(foodId: UUID, position: Int): ResponseEntity<Any> {
        val food = foodRepository.getReferenceById(foodId)
        val newFoodPhotos = food.photos?.filter { foodPhoto ->  foodPhoto.position != position}?.toMutableSet()
        val newFood = Food(
            food.id,
            food.foodName,
            food.foodNameSl,
            food.gen,
            food.processing,
            food.portionSize,
            food.energyKj,
            food.energyKcal,
            food.protein,
            food.carbs,
            food.sugars,
            food.dietaryFibre,
            food.fat,
            food.saturated,
            food.ca,
            food.fe,
            food.mg,
            food.k,
            food.na,
            food.zn,
            food.carotenoide,
            food.retinol,
            food.thiamin,
            food.riboflavin,
            food.niacin,
            food.b6,
            food.b12,
            food.folate,
            food.vitaminC,
            food.vitaminD,
            food.vitaminE,
            food.ingredients,
            food.images,
            newFoodPhotos,
            food.art,
            food.stages,
            food.tag,
            food.meals,
            food.instances
        )
        return if (foodRepository.existsById(food.id!!)) {
            food.id = food.id
           // foodRepository.save(newFood)
            val savedFoodPhotos = foodRepository.save(newFood).photos
            ResponseEntity.status(HttpStatus.OK).body(savedFoodPhotos)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}