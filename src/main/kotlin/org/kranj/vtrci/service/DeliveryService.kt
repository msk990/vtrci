package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.DeliveryDto
import org.kranj.vtrci.dtos.ItemDto
import org.kranj.vtrci.model.Delivery
import org.kranj.vtrci.model.OrderEntity
import org.kranj.vtrci.repository.DeliveryRepository
import org.kranj.vtrci.transformer.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.util.*

@Service
class DeliveryService(
    val repository: DeliveryRepository,
    val userService: UserService,
    val notificationService: NotificationService,
    val inventoryService: InventoryItemService
) {

    fun getMyDeliveries() : List<DeliveryDto>? {
        val user = userService.getOrgFromUser()
        val orgId = user?.id
        return repository.findByBuyer_IdEqualsOrOrder_Seller_IdEquals(orgId, orgId).map(Delivery::toDeliveryDto)
    }

    fun getMySellerDeliveriesBetween(start: Instant, end: Instant) : List<DeliveryDto>? {
        val user = userService.getOrgFromUser()
        val orgId = user?.id
        return repository.findByBuyer_IdEqualsOrOrder_Seller_IdEqualsAndDeliveryDateBetween(orgId, orgId, start, end).map(Delivery::toDeliverySellerDto)
    }

    fun getMyBuyerDeliveriesBetween(start: Instant, end: Instant) : List<DeliveryDto>? {
        val user = userService.getOrgFromUser()
        val orgId = user?.id
        return repository.findByBuyer_IdEqualsOrOrder_Seller_IdEqualsAndDeliveryDateBetween(orgId, orgId, start, end).map(Delivery::toDeliveryBuyerDto)
    }

    fun getById(id: UUID): DeliveryDto = repository.findByIdOrNull(id)?.toDeliveryDto() ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun changeStatus(id:UUID, status:String) : Delivery {
         if (repository.existsById(id)) {
           val oldDelivery = repository.getReferenceById(id)
             val newDelivery = Delivery(
                 id = oldDelivery.id,
                 item = oldDelivery.item,
                 quantity = oldDelivery.quantity,
                 unit = oldDelivery.unit,
                 seller = oldDelivery.seller,
                 buyer = oldDelivery.buyer,
                 deliverer = oldDelivery.deliverer,
                 deliveryDate = oldDelivery.deliveryDate,
                 inventory= oldDelivery.inventory,
                 status = status,
             )
             newDelivery.order = oldDelivery.order

             return repository.save(newDelivery)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun askToDeliver(id:UUID) : Delivery {
        if (repository.existsById(id)) {
            val oldDelivery = repository.getReferenceById(id)
            val newDelivery = Delivery(
                id = oldDelivery.id,
                item = oldDelivery.item,
                quantity = oldDelivery.quantity,
                unit = oldDelivery.unit,
                seller = oldDelivery.seller,
                buyer = oldDelivery.buyer,
                deliverer = oldDelivery.deliverer,
                deliveryDate = oldDelivery.deliveryDate,
                inventory= oldDelivery.inventory,
                status = "deliverying",
            )
            newDelivery.order = oldDelivery.order
            notificationService.createDeliveryNotification(oldDelivery.buyer?.id, oldDelivery.id, "Delivery confirmation request")
            return repository.save(newDelivery)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    }

    fun askConfirmDelivery(id:UUID, notifId:UUID) : Delivery {
        if (repository.existsById(id)) {
            val oldDelivery = repository.getReferenceById(id)
            val newDelivery = Delivery(
                id = oldDelivery.id,
                item = oldDelivery.item,
                quantity = oldDelivery.quantity,
                unit = oldDelivery.unit,
                seller = oldDelivery.seller,
                buyer = oldDelivery.buyer,
                deliverer = oldDelivery.deliverer,
                deliveryDate = oldDelivery.deliveryDate,
                inventory= oldDelivery.inventory,
                status = "delivered",
            )
            newDelivery.order = oldDelivery.order
            notificationService.createDeliveryNotification(oldDelivery.seller?.id, oldDelivery.id, "Delivery confirmed")
            notificationService.changeStatus(notifId)
            if(oldDelivery.inventory != null){
            for(inventory in oldDelivery.inventory){
                inventoryService.changeOwner(inventory.id, oldDelivery.buyer)
            }
            }

            return repository.save(newDelivery)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    }

}