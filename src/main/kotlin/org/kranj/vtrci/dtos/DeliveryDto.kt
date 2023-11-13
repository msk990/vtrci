package org.kranj.vtrci.dtos

import org.kranj.vtrci.model.InventoryItem
import org.kranj.vtrci.model.Organization
import java.time.Instant
import java.util.UUID

class DeliveryDto(
    val id: UUID?,
    val itemName: String?,
    val itemNameSl: String?,
    val itemId: UUID?,
    val quantity: Double?,
    val unit: String?,
    val title: String?,
    val sellerName: String?,
    val buyerName: String?,
    val start: Instant,
    val inventory: List<InventoryItem>?,
    val status: String,
    val allDay: Boolean,
    val color: String
) {

}

class DeliveryChangeDto(
    val id: UUID,
    val status: String
)
{

}