package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.DeliveryDto
import org.kranj.vtrci.model.Delivery

fun Delivery.toDeliveryDto() : DeliveryDto {

    val color: String = when(this.status) {
        "unpacked" -> "#FCCA46"
        "delivered" -> "#6CAE75"
        "packed" -> "#FE7F2D"
        "shipping" -> "#3788D8"
        "returned" -> "#FC60A8"
        else -> "#FCCA46"
    }
    return DeliveryDto(
        id = this.id,
        itemName = this.item.itemName,
        itemNameSl = this.item.itemNameSl,
        itemId = this.item.id,
        quantity = this.quantity,
        unit = this.unit,
        title = this.quantity.toString() + " " +this.unit + " " + this.item.itemName,
        sellerName = this.seller?.organizationName,
        buyerName = this.buyer?.organizationName,
        start = this.deliveryDate,
        inventory = this.inventory,
        status = this.status,
        allDay = true,
        color = color


    )
}

    fun Delivery.toDeliverySellerDto() : DeliveryDto {

        val color: String = when(this.status) {
            "unpacked" -> "#FCCA46"
            "delivered" -> "#6CAE75"
            "packed" -> "#FE7F2D"
            "shipping" -> "#3788D8"
            "returned" -> "#FC60A8"
            else -> "#FCCA46"
        }
        return DeliveryDto(
            id = this.id,
            itemName = this.item.itemName,
            itemNameSl = this.item.itemNameSl,
            itemId = this.item.id,
            quantity = this.quantity,
            unit = this.unit,
            title = this.quantity.toString() + " " +this.unit + " " + this.item.itemName  + " " + this.seller?.organizationName,
            sellerName = this.seller?.organizationName,
            buyerName = this.buyer?.organizationName,
            start = this.deliveryDate,
            inventory = this.inventory,
            status = this.status,
            allDay = true,
            color = color


        )
    }

fun Delivery.toDeliveryBuyerDto() : DeliveryDto {

    val color: String = when(this.status) {
        "unpacked" -> "#FCCA46"
        "delivered" -> "#6CAE75"
        "packed" -> "#FE7F2D"
        "shipping" -> "#3788D8"
        "returned" -> "#FC60A8"
        else -> "#FCCA46"
    }
    return DeliveryDto(
        id = this.id,
        itemName = this.item.itemName,
        itemNameSl = this.item.itemNameSl,
        itemId = this.item.id,
        quantity = this.quantity,
        unit = this.unit,
        title = this.quantity.toString() + " " +this.unit + " " + this.item.itemName  + " " + this.buyer?.organizationName,
        sellerName = this.seller?.organizationName,
        buyerName = this.buyer?.organizationName,
        start = this.deliveryDate,
        inventory = this.inventory,
        status = this.status,
        allDay = true,
        color = color


    )
}

