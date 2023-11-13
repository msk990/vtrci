package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.NewImageDto
import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.FoodImage
import org.kranj.vtrci.repository.FoodImageRepository
import org.kranj.vtrci.repository.FoodRepository
import org.kranj.vtrci.transformer.toFood
import org.springframework.core.io.ClassPathResource
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import java.util.*


@Service
class FoodImageService (var repository: FoodImageRepository,
val foodRepository: FoodRepository,
                        private val fileStorageService: FileStorageService
) {

    fun getAll(): List<FoodImage> = repository.findAll()
    fun getById(id: UUID): FoodImage = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(img:NewImageDto): ResponseEntity<Any>{
//        val path = Files.createDirectories(Paths.get("public/Your/Path/Here"))
//        val pathDirectory:String = path.toString()
        val food = foodRepository.getReferenceById(img.foodId)
//        val file = img.file
//        var fileName = LocalDateTime.now().toString() + file.originalFilename
//
//        try {
//            file.transferTo(File(pathDirectory+File.separator+fileName))
//        } catch (e: Exception) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build<Any>()
//        }

        val fileName = fileStorageService.storeFile(img.file)
        val foodImage = FoodImage(UUID.randomUUID(),position=img.position, name = fileName)
      //  return ResponseEntity.status(HttpStatus.OK).body(repository.save(foodImage))
        var newFoodImages = food.images?.filter { image -> image.position != img.position }?.toMutableSet()
        newFoodImages?.add(foodImage)

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
            newFoodImages,
            food.art,
            food.stages,
            food.tag,
            food.meals,
            food.instances
        )
        return if (foodRepository.existsById(food.id!!)) {
            food.id = food.id
            val savedFoodImage = foodRepository.save(newFood).images?.filter { it.position == img.position }
            ResponseEntity.status(HttpStatus.OK).body(savedFoodImage)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(foodId: UUID, position: Int): ResponseEntity<Any> {
        val food = foodRepository.getReferenceById(foodId)
        val newFoodImages = food.images?.filter { foodImage ->  foodImage.position != position}?.toMutableSet()
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
            newFoodImages,
            food.art,
            food.stages,
            food.tag,
            food.meals,
           food.instances
        )
        return if (foodRepository.existsById(food.id!!)) {
            food.id = food.id

            val savedFoodImage = foodRepository.save(newFood).images
            ResponseEntity.status(HttpStatus.OK).body(savedFoodImage)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}