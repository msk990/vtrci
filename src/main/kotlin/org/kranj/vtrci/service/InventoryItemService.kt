package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.InventoryDto
import org.kranj.vtrci.dtos.NewInventoryDto
import org.kranj.vtrci.dtos.UpdateInventoryDto
import org.kranj.vtrci.model.InventoryItem
import org.kranj.vtrci.model.Organization
import org.kranj.vtrci.repository.InventoryItemRepository
import org.kranj.vtrci.repository.ItemsRepository
import org.kranj.vtrci.repository.OrganizationRepository
import org.kranj.vtrci.repository.ProductRepository
import org.kranj.vtrci.transformer.toFoodDto
import org.kranj.vtrci.transformer.toInventoryDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.util.*

@Service
class InventoryItemService(
    val inventoryRepository: InventoryItemRepository,
    val itemsRepository: ItemsRepository,
    val productRepository: ProductRepository,
    val organizationRepository: OrganizationRepository,
    val userService: UserService

) {
    fun getAll(): List<InventoryDto> = inventoryRepository.findAll().map(InventoryItem::toInventoryDto)

    fun getMine(): List<InventoryDto>? {
        val weAre: Organization? = userService.getOrgFromUser()
        return if (weAre != null) {
            inventoryRepository.findByOwner_IdOrCustodian_IdEquals(weAre.id, weAre.id).map(InventoryItem::toInventoryDto)
        } else null
    }
    fun getById(id: UUID): InventoryDto = inventoryRepository.findByIdOrNull(id)?.toInventoryDto() ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getByItemId(itemId:UUID) {
        val producer = userService.getOrgFromUser()
        val orgId = producer?.id
        inventoryRepository.findByProduct_Item_Id(itemId)
    }

    fun getByProductAndOwner(productId:UUID) {
        val producer = userService.getOrgFromUser()
        val orgId = producer?.id
        inventoryRepository.findByProductId(productId)
    }

    fun getByOwner(owner:String) = inventoryRepository.findByOwnerEquals(owner)

    fun create(inv:NewInventoryDto): InventoryItem {
        val product = productRepository.getReferenceById(inv.productId)
       // val item = product.item.id?.let { itemsRepository.getReferenceById(it) }

        val weAre: Organization? = userService.getOrgFromUser()
        val newInventoryItem = InventoryItem(
            UUID.randomUUID(),
         //   item,
            inv.quantity,
            inv.unit,
            product,
            inv.lotNumber,
            Instant.ofEpochMilli(inv.productionDate),
            Instant.ofEpochMilli(inv.expirationDate),
            weAre,
            weAre,
            "available",
            listOf()
        )
        return inventoryRepository.save(newInventoryItem)

     }

    fun update(inv: UpdateInventoryDto){
        val invId = UUID.fromString(inv.id)
        val inventory = inventoryRepository.getReferenceById(invId)
       val status = if (inv.quantity > 0)  "active" else "packed"
        if (inventoryRepository.existsById(invId)){
           val newInventoryItem = InventoryItem(
               id = invId,
           //    item = inventory.item,
               quantity = inv.quantity,
               unit = inventory.unit,
               product = inventory.product,
               lotNumber = inventory.lotNumber,
               productionDate = inventory.productionDate,
               expirationDate = inventory.expirationDate,
               owner = inventory.owner,
               custodian = inventory.custodian,
               status = status,
               deliveries = inventory.deliveries,
           )
        inventoryRepository.save(newInventoryItem)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    }

    fun changeOwner(invId:UUID?, owner:Organization?){
        val inventory = invId?.let { inventoryRepository.getReferenceById(it) }
       if(inventory?.id != null) {
           val newInventoryItem = InventoryItem(
               id = invId,
               //    item = inventory.item,
               quantity = inventory.quantity,
               unit = inventory.unit,
               product = inventory.product,
               lotNumber = inventory.lotNumber,
               productionDate = inventory.productionDate,
               expirationDate = inventory.expirationDate,
               owner = owner,
               custodian = inventory.custodian,
               status = "available",
               deliveries = inventory.deliveries,
           )
           inventoryRepository.save(newInventoryItem)
       }
       else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
    fun remove(id: UUID) {
        if (inventoryRepository.existsById(id)) inventoryRepository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

//    fun changeOwner(id: UUID) {
//        val item = inventoryRepository.getReferenceById(id)
//        val newItem = InventoryItem(
//            id,
//            item.item,
//            item.quantity,
//            item.unit,
//            item.product,
//            item.lotNumber,
//            item.productionDate,
//            item.expirationDate,
//           "kidz"
//        )
//    }
}