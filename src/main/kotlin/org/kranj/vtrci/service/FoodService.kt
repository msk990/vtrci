package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.FoodDto
import org.kranj.vtrci.dtos.ItemDto
import org.kranj.vtrci.dtos.NewFoodDto
import org.kranj.vtrci.model.Food
import org.kranj.vtrci.repository.FoodRepository
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
                    private val addFoodTransformer: AddFoodTransformer,
private val foodPageableTransformer: FoodPageableTransformer
                   ) {
//    fun getAll(): List<Food> = repository.findAll()
    fun getAll(): List<FoodDto> = repository.findAll().map(Food::toFoodDto)

    fun getById(id: UUID): FoodDto = repository.findByIdOrNull(id)?.toFoodDto() ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

  //  fun create(food: Food): Food = repository.save(food)
  fun getByContentAndTag(name: String, tag:UUID?, pageable: Pageable): Page<FoodDto> {
//        return if (tag.toString().isEmpty()) {
//            repository.findByItemNameContainingIgnoreCase(name,pageable)
//        } else {
//            repository.findByItemNameContainingIgnoreCaseAndTagIdEquals(name, tag, pageable)
//
//        }
      return  foodPageableTransformer.transform(repository.findByFoodNameContainingIgnoreCaseAndTagIdEquals(name, tag, pageable))
  }

    fun create(food: NewFoodDto): Food = repository.save(addFoodTransformer.transform(food))

    fun remove(id: UUID) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id:UUID, food: FoodDto): Food {
        return if (repository.existsById(id)) {
            food.id = id
            repository.save(food.toFood())
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}