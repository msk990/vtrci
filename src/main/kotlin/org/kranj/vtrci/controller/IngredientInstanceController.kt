package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.IngredientInstanceDto
import org.kranj.vtrci.model.IngredientInstance
import org.kranj.vtrci.service.IngredientInstanceService
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/ingredient-instance")
@RestController
class IngredientInstanceController(
    val service: IngredientInstanceService
) {
    @CrossOrigin
    @PutMapping()
    fun updateIngredientInstance(@RequestBody ingredient: IngredientInstanceDto) = service.update(ingredient)

}