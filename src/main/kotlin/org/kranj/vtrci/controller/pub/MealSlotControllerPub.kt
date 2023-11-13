package org.kranj.vtrci.controller.pub

import org.kranj.vtrci.dtos.MealSlotDto
import org.kranj.vtrci.model.MealSlot
import org.kranj.vtrci.service.MealSlotService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/pub/meal-slot")
@RestController
class MealSlotControllerPub (val service: MealSlotService) {

    @CrossOrigin
    @GetMapping
    fun getAllSlots() = service.getAll()

    @CrossOrigin
    @GetMapping("/{id}")
    fun getSlot(@PathVariable id: Int) = service.getById(id)
//
//    @CrossOrigin
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody()
//    fun saveSlot(@RequestBody slot: MealSlotDto) : MealSlot = service.addArt(slot.frontArtId, slot.position)
//
//    @CrossOrigin
//    @DeleteMapping("/{position}")
//    fun jujeto(@PathVariable position:Int) = service.deleteArt(position)

}