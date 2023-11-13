package org.kranj.vtrci.controller


import org.kranj.vtrci.dtos.*
import org.kranj.vtrci.model.InventoryItem
import org.kranj.vtrci.model.Product
import org.kranj.vtrci.service.InventoryItemService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/inventory")
@RestController
class InventoryItemController(val service: InventoryItemService) {

    @CrossOrigin
    @GetMapping
    fun getAllInventory() = service.getAll()

    @CrossOrigin
    @GetMapping("/poochie")
    fun getByItemId(
                        @RequestParam("itemId") itemId: UUID
    ) = service.getByItemId(itemId)

    @CrossOrigin
    @GetMapping("/hobbit")
    fun getByProductId(
        @RequestParam("id") id: String,
    ) = service
       // .getMine()
      //  .getByItemId(UUID.fromString("2a19d5e4-424b-4133-9f7f-4d026ed4a9d5"))
        .getByProductAndOwner(UUID.fromString(id))

    @CrossOrigin
    @GetMapping("/owner")
    fun getByOwner(
        @RequestParam("owner") owner: String
    ) = service.getByOwner(owner)

    @CrossOrigin
    @GetMapping("/{id}")
    fun getItem(@PathVariable id: UUID) = service.getById(id)

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveItem(@RequestBody item: NewInventoryDto): InventoryItem = service.create(item)

    @CrossOrigin
    @PutMapping
    fun updateItem(@RequestBody item: UpdateInventoryDto) = service.update(item)
    @CrossOrigin
    @DeleteMapping("/{id}")
    fun deleteFood(@PathVariable id: UUID) = service.remove(id)

//    @CrossOrigin
//    @PutMapping("/{id}")
//    fun changeOwner(@PathVariable id: UUID) = service.changeOwner(id)
//

}