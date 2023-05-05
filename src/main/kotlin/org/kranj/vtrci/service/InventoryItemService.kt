package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.FoodDto
import org.kranj.vtrci.dtos.InventoryDto
import org.kranj.vtrci.dtos.NewInventoryDto
import org.kranj.vtrci.model.InventoryItem
import org.kranj.vtrci.repository.InventoryItemRepository
import org.kranj.vtrci.repository.ItemsRepository
import org.kranj.vtrci.repository.ProductRepository
import org.kranj.vtrci.transformer.toFoodDto
import org.kranj.vtrci.transformer.toInventoryDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class InventoryItemService(
    val inventoryRepository: InventoryItemRepository,
    val itemsRepository: ItemsRepository,
    val productRepository: ProductRepository
) {
    fun getAll(): List<InventoryDto> = inventoryRepository.findAll().map(InventoryItem::toInventoryDto)

    fun getById(id: UUID): InventoryDto = inventoryRepository.findByIdOrNull(id)?.toInventoryDto() ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getByItemId(itemId:UUID) = inventoryRepository.findByItem(itemId)

    fun getByOwner(owner:String) = inventoryRepository.findByOwnerEquals(owner)

    fun create(inv:NewInventoryDto): InventoryItem {
        val product = productRepository.getReferenceById(inv.productId)
        val item = product.item.id?.let { itemsRepository.getReferenceById(it) }

        val newInventoryItem = InventoryItem(
            UUID.randomUUID(),
            item,
            inv.quantity,
            product,
            inv.lotNumber,
            inv.productionDate,
            inv.expirationDate,
            inv.owner
        )
        return inventoryRepository.save(newInventoryItem)

     }
    fun remove(id: UUID) {
        if (inventoryRepository.existsById(id)) inventoryRepository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun changeOwner(id: UUID) {
        val item = inventoryRepository.getReferenceById(id)
        val newItem = InventoryItem(
            id,
            item.item,
            item.quantity,
            item.product,
            item.lotNumber,
            item.productionDate,
            item.expirationDate,
           "kidz"
        )
    }
}