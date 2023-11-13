package org.kranj.vtrci.controller.pub

import org.kranj.vtrci.dtos.ItemDto
import org.kranj.vtrci.dtos.NewItemDto
import org.kranj.vtrci.model.Item
import org.kranj.vtrci.service.ItemsService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*


@RequestMapping("api/v1/pub/items")
@RestController
class ItemControllerPub (val service: ItemsService){
    @CrossOrigin
    @GetMapping
    fun getAllItems(pageable: Pageable) = service.getAll(pageable)


    @CrossOrigin
    @GetMapping("/juje")
    fun getByName(@RequestParam("term") term: String,
                  pageable: Pageable
    ) = service.getByName(term, pageable)

    @CrossOrigin
    @GetMapping("/poochie")
    fun getByNameAndTag(@RequestParam("term") term: String,
                        @RequestParam("tag") tag: UUID?,
                        pageable: Pageable
    ) = service.getByContentAndTag(term, tag, pageable)

    @CrossOrigin
    @GetMapping("/hobbit")
    fun getByHobbit(@RequestParam("term") term: String,
                    @RequestParam("tags") tags: List<String>,
                    pageable: Pageable
    ) = service.geByContent(term, tags, pageable)


    @CrossOrigin
    @GetMapping("/{id}")
    fun getItem(@PathVariable id: UUID) = service.getById(id)
//
//    @CrossOrigin
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    fun saveItem(@RequestBody item: NewItemDto): Item = service.create(item)
//
////    @CrossOrigin
////    @PostMapping
////    @ResponseStatus(HttpStatus.CREATED)
////    fun saveItem(@RequestBody item: NewItemDto): Item = service.create(item)
//
//
//    @CrossOrigin
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    fun deleteItem(@PathVariable id: UUID) = service.remove(id)
//
//    @CrossOrigin
//    @PutMapping("/{id}")
//    fun updateItem(@PathVariable id: UUID, @RequestBody item: ItemDto) = service.update(id, item)
}