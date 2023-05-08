package org.kranj.vtrci.controller

import org.kranj.vtrci.dtos.NewItemDto
import org.kranj.vtrci.dtos.NewProductDto
import org.kranj.vtrci.model.Item
import org.kranj.vtrci.model.Product
import org.kranj.vtrci.service.ProductService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/products")
@RestController
class ProductController(val service: ProductService) {
    @CrossOrigin
    @GetMapping
    fun getAllProducts(pageable: Pageable) = service.getAll(pageable)

    @CrossOrigin
    @GetMapping("/poochie")
    fun getByItemIdIn(@RequestParam("itemIds") itemIds: List<UUID>) = service.getByItemIdIn(itemIds)


//    @CrossOrigin
//    @GetMapping("/poochie")
//    fun getByNameAndTag(@RequestParam("term") term: String,
//                        @RequestParam("tag") tag: UUID?,
//                        pageable: Pageable) = service.getByContentAndTag(term, tag, pageable)

    @CrossOrigin
    @GetMapping("/{id}")
    fun getItem(@PathVariable id: UUID) = service.getById(id)

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveItem(@RequestBody item: NewProductDto): Product = service.create(item)

    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteItem(@PathVariable id: UUID) = service.remove(id)

}