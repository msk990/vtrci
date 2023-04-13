package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.ItemDto
import org.kranj.vtrci.dtos.NewItemDto
import org.kranj.vtrci.model.Item
import org.kranj.vtrci.repository.ItemsRepository
import org.kranj.vtrci.transformer.AddItemTransformer
import org.kranj.vtrci.transformer.ItemPageableTransformer
import org.kranj.vtrci.transformer.toItem
import org.kranj.vtrci.transformer.toItemDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
class ItemsService (val repository: ItemsRepository,
private val addItemTransformer: AddItemTransformer,
private val itemPageableTransformer: ItemPageableTransformer){
    fun getAll(pageable: Pageable): Page<Item> = repository.findAll(pageable)

    fun getByName(name: String, pageable: Pageable): Page<Item?> = repository.findByItemNameContainingIgnoreCase(name, pageable)

    fun geByContent(name: String,tags:List<String>, pageable: Pageable): Page<Item?> = repository.findByItemNameContainingIgnoreCase(name, pageable)

    fun getByContentAndTag(name: String, tag:UUID?, pageable: Pageable): Page<ItemDto>? {
//        return if (tag.toString().isEmpty()) {
//            repository.findByItemNameContainingIgnoreCase(name,pageable)
//        } else {
//            repository.findByItemNameContainingIgnoreCaseAndTagIdEquals(name, tag, pageable)
//
//        }
       return  itemPageableTransformer.transform(repository.findByItemNameContainingIgnoreCaseAndTagIdEquals(name, tag, pageable))
    }

    fun getById(id: UUID): ItemDto = repository.findByIdOrNull(id)?.toItemDto() ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(item: NewItemDto): Item = repository.save(addItemTransformer.transform(item))

    fun remove(id: UUID) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id:UUID, item: ItemDto): Item {
        return if (repository.existsById(id)) {
            item.id = id
            repository.save(item.toItem())
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
};