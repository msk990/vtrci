package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.IngredientInstanceDto
import org.kranj.vtrci.model.IngredientInstance
import org.kranj.vtrci.repository.IngredientInstanceRepository
import org.kranj.vtrci.repository.IngredientsRepository
import org.kranj.vtrci.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class IngredientInstanceService (
    val repository: IngredientInstanceRepository,
    val ingredientsRepository: IngredientsRepository,
    val productRepository: ProductRepository
        ){

    fun update(instDto:IngredientInstanceDto){

        val oldInst = repository.getReferenceById(instDto.instId)

        var newInst = IngredientInstance(
            oldInst.id,
            oldInst.ingredient,
            instDto.eco,
            instDto.local,
            instDto.quality,
            instDto.schema,
            instDto.garden,
            instDto.productId?.let { productRepository.getReferenceById(it) }

        )
        newInst.foodInstance = oldInst.foodInstance
        repository.save(newInst)

    }
}