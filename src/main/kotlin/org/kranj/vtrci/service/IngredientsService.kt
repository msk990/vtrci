package org.kranj.vtrci.service

import org.kranj.vtrci.model.Ingredient

import org.kranj.vtrci.repository.IngredientsRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class IngredientsService(val repository: IngredientsRepository) {

    fun getAll(pageable: Pageable): Page<Ingredient> = repository.findAll(pageable)

    fun getById(id: UUID):Ingredient = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(ingredient: Ingredient):Ingredient = repository.save(ingredient)

    fun remove(id: UUID) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

        fun update(id: UUID,ingredient:Ingredient):Ingredient {
            return if (repository.existsById(id)) {
                ingredient.id = id
                repository.save(ingredient)
            } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
}