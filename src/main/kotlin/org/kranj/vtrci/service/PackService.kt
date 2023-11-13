package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.InventoryForDelivery
import org.kranj.vtrci.dtos.InventorySplitDto
import org.kranj.vtrci.dtos.PackedDeliveryDto
import org.kranj.vtrci.model.Delivery
import org.kranj.vtrci.model.InventoryItem
import org.kranj.vtrci.repository.DeliveryRepository
import org.kranj.vtrci.repository.InventoryItemRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class PackService(
    val inventoryRepository: InventoryItemRepository,
    val deliveryRepository: DeliveryRepository,
    val userService: UserService
) {

    fun update (pack:PackedDeliveryDto) : Delivery {

        if (deliveryRepository.existsById(UUID.fromString(pack.deliveryId))) {
        val oldDelivery = deliveryRepository.getReferenceById(UUID.fromString(pack.deliveryId))
        var inventory: MutableList<InventoryItem> = mutableListOf()
         for (i:InventoryForDelivery in pack.inventory) {
             val newInventory = inventorySplitHelper(i, delivery = oldDelivery)
             inventory.add(newInventory)
         }

            val newDelivery = Delivery(
                id = oldDelivery.id,
                item = oldDelivery.item,
                quantity = oldDelivery.quantity,
                unit = oldDelivery.unit,
                seller = oldDelivery.seller,
                buyer = oldDelivery.buyer,
                deliverer = oldDelivery.deliverer,
                deliveryDate = oldDelivery.deliveryDate,
                inventory= inventory.toList(),
                status = "packed",


            )
            newDelivery.order = oldDelivery.order
            return deliveryRepository.save(newDelivery)

        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun inventorySplitHelper(inv: InventoryForDelivery, delivery: Delivery) : InventoryItem {
        val oldId = UUID.fromString(inv.id)
        val oldInventory = inventoryRepository.getReferenceById(oldId)

        if (oldInventory.quantity == inv.quantity) {
            val newInventoryItem = InventoryItem(
                id = oldId,
                quantity = inv.quantity,
                unit = oldInventory.unit,
                product = oldInventory.product,
                lotNumber = oldInventory.lotNumber,
                productionDate = oldInventory.productionDate,
                expirationDate = oldInventory.expirationDate,
                owner =oldInventory.owner,
                custodian = oldInventory.custodian,
                status = "packed",
                deliveries = listOf(delivery),
            )
//            val inventorySplit = InventorySplitDto(
//                newInventory = newInventoryItem,
//                remainingInventory = null
//            )
            inventoryRepository.save(newInventoryItem)
            return newInventoryItem
        }
        else if (oldInventory.quantity!! > inv.quantity){
            val newInventoryItem = InventoryItem(
                id = UUID.randomUUID(),
                quantity = inv.quantity,
                unit = oldInventory.unit,
                product = oldInventory.product,
                lotNumber = oldInventory.lotNumber,
                productionDate = oldInventory.productionDate,
                expirationDate = oldInventory.expirationDate,
                owner =oldInventory.owner,
                custodian = oldInventory.custodian,
                status = "packed",
                deliveries = listOf(delivery),
            )
           // inventoryRepository.save(newInventoryItem)
            val remainInventoryItem = InventoryItem(
                id = oldId,
                quantity = oldInventory.quantity - inv.quantity,
                unit = oldInventory.unit,
                product = oldInventory.product,
                lotNumber = oldInventory.lotNumber,
                productionDate = oldInventory.productionDate,
                expirationDate = oldInventory.expirationDate,
                owner =oldInventory.owner,
                custodian = oldInventory.custodian,
                status = oldInventory.status,
                deliveries = listOf(),
            )
            inventoryRepository.save(remainInventoryItem)
//            val inventorySplit = InventorySplitDto(
//                newInventory = newInventoryItem,
//                remainingInventory = remainInventoryItem
//            )
            return  inventoryRepository.save(newInventoryItem)
        }
        else throw ResponseStatusException(HttpStatus.EXPECTATION_FAILED)
    }
}