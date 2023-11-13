package org.kranj.vtrci.controller

import org.kranj.vtrci.service.InventoryNativeService
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/inventory-native")
@RestController
class InventoryNativeController (val service: InventoryNativeService) {
    @CrossOrigin
    @GetMapping("/hobbit")
    fun getByProductId(
        @RequestParam("id") id: String,
    ) = service
        // .getMine()
        //  .getByItemId(UUID.fromString("2a19d5e4-424b-4133-9f7f-4d026ed4a9d5"))
        .getByProductAndOwner(UUID.fromString(id))

    @CrossOrigin
    @GetMapping("/poochietu")
    fun getByItemId(
        @RequestParam("id") id: String,
    ) = service
        // .getMine()
        //  .getByItemId(UUID.fromString("2a19d5e4-424b-4133-9f7f-4d026ed4a9d5"))
        .getByItemAndOwner(UUID.fromString(id))

    @CrossOrigin
    @GetMapping("/available")
    fun getAvailableByItemId(
        @RequestParam("id") id: String,
    ) = service
        // .getMine()
        //  .getByItemId(UUID.fromString("2a19d5e4-424b-4133-9f7f-4d026ed4a9d5"))
        .getUnpackedByItemAndOwner(UUID.fromString(id))

}