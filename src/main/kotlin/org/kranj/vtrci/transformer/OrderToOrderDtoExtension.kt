package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.DeliveryForOrder
import org.kranj.vtrci.dtos.OrderDto
import org.kranj.vtrci.model.Delivery
import org.kranj.vtrci.model.OrderEntity

fun OrderEntity.toOrderDto() : OrderDto {

    var newDeliveries: MutableList<DeliveryForOrder> = mutableListOf()
    if (this.deliveries.isNullOrEmpty()) {

    } else {
        for (delivery in this.deliveries!!){
            newDeliveries.add(delivery.toDeliveryForOrder())
        }
    }
    return OrderDto(
       id = this.id,
        itemId = this.item.id,
        quantity = this.quantity,
        unit = this.unit,
        itemName = this.item.itemName,
        itemNameSl = this.item.itemNameSl,
        sellerName = this.seller?.organizationName,
        buyerName = this.buyer?.organizationName,
        status = this.status,
        deliveries = newDeliveries.toList()


    )
}

fun Delivery.toDeliveryForOrder() : DeliveryForOrder {
   return DeliveryForOrder(
       deliveryDate = this.deliveryDate
   )
}