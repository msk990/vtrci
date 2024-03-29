package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.FoodDto
import org.kranj.vtrci.dtos.FoodReturnDto
import org.kranj.vtrci.dtos.NewFoodDto
import org.kranj.vtrci.model.Food
import org.kranj.vtrci.repository.ArtRepository
import org.kranj.vtrci.repository.FrontArtRepository
import org.kranj.vtrci.repository.FoodRepository
import org.kranj.vtrci.repository.IngredientsRepository
import org.kranj.vtrci.transformer.AddFoodTransformer
import org.kranj.vtrci.transformer.FoodPageableTransformer
import org.kranj.vtrci.transformer.toFood
import org.kranj.vtrci.transformer.toFoodDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
class FoodService (val repository:FoodRepository,
                   val ingredientsRepository: IngredientsRepository,
                   private val addFoodTransformer: AddFoodTransformer,
                   val artRepository: ArtRepository,
                   val ingredientsService: IngredientsService,
                   private val foodPageableTransformer: FoodPageableTransformer
                   ) {
//    fun getAll(): List<Food> = repository.findAll()
    fun getAll(): List<FoodDto> = repository.findAll().map(Food::toFoodDto)

    fun getById(id: UUID): FoodDto = repository.findByIdOrNull(id)?.toFoodDto() ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

  //  fun create(food: Food): Food = repository.save(food)
  fun getByContentAndTag(name: String, tag:UUID?, pageable: Pageable): Page<FoodDto> {
        return if (tag == null) {
            foodPageableTransformer.transform(repository.findByFoodNameContainingIgnoreCaseOrFoodNameSlContainingIgnoreCase(name,name,pageable))
        } else {
            foodPageableTransformer.transform(repository.findByTagIdEquals(tag, pageable))

        }
    //  return  foodPageableTransformer.transform(repository.findByFoodNameContainingIgnoreCaseAndTagIdEquals(name, tag, pageable))
  }

    fun create(food: NewFoodDto): FoodDto = repository.save(addFoodTransformer.transform(food)).toFoodDto()

    fun remove(id: UUID) {
        if (repository.existsById(id)) {
//            val food = repository.getReferenceById(id)
//            food.ingredients?.forEach{
//                it.id?.let { it1 -> ingredientsService.remove(it1) }
//            }
            repository.deleteById(id)
        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id:UUID, food: FoodReturnDto): Food {
        return if (repository.existsById(id)) {
            food.id = id
          // ingredientsRepository.deleteFromFoodRelationship(foodId = id)
            repository.save(food.toFood(repository, ingredientsService))
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun addArt(artId: UUID, foodId: UUID):Food {
        val food = repository.getReferenceById(foodId)
        val art = artRepository.getReferenceById(artId)
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
            food.photos,
            art,
            food.stages,
            food.tag,
            food.meals,
            food.instances
        )
       return repository.save(newFood)
       // return newFood

    }

    fun removeArt(foodId: UUID):Food {
        val food = repository.getReferenceById(foodId)

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
            food.photos,
            null,
            food.stages,
            food.tag,
            food.meals,
            food.instances
        )
        return repository.save(newFood)

    }
}