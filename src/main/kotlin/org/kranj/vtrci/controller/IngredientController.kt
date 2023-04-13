package org.kranj.vtrci.controller

import org.kranj.vtrci.model.Ingredient
import org.kranj.vtrci.service.IngredientsService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/ingredients")
@RestController
class IngredientController(val service: IngredientsService) {

    @GetMapping
    fun getAllPlayers(pageable: Pageable) = service.getAll(pageable)

    @GetMapping("/{id}")
    fun getPlayer(@PathVariable id: UUID) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun savePlayer(@RequestBody ingredient: Ingredient): Ingredient = service.create(ingredient)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePlayer(@PathVariable id: UUID) = service.remove(id)

    @PutMapping("/{id}")
    fun updatePlayer(
        @PathVariable id: UUID, @RequestBody player: Ingredient
    ) = service.update(id, player)
}