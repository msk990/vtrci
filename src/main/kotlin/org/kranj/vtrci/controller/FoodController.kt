package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.FoodDto
import org.kranj.vtrci.dtos.NewFoodDto
import org.kranj.vtrci.model.Food
import org.kranj.vtrci.service.FoodService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RequestMapping("api/v1/foods")
@RestController
class FoodController (val service: FoodService) {

    @CrossOrigin
    @GetMapping
    fun getAllFoods() = service.getAll()

    @CrossOrigin
    @GetMapping("/poochie")
    fun getByNameAndTag(@RequestParam("term") term: String,
                        @RequestParam("tag") tag: UUID?,
                        pageable: Pageable
    ) = service.getByContentAndTag(term, tag, pageable)


    @CrossOrigin
    @GetMapping("/{id}")
    fun getFood(@PathVariable id: UUID) = service.getById(id)


    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody()
    fun saveFood(@RequestBody food:NewFoodDto) : FoodDto = service.create(food)

//    @CrossOrigin
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    fun saveFood(@RequestBody food:Food) : Food = service.create(food)

    @CrossOrigin
    @DeleteMapping("/{id}")
    fun deleteFood(@PathVariable id: UUID) = service.remove(id)

    @CrossOrigin
    @PutMapping("/{id}")
    fun updateFood(@PathVariable id: UUID, @RequestBody food:FoodDto) = service.update(id, food)


}