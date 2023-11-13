package org.kranj.vtrci.dtos

import java.time.Instant
import java.util.*

class NewOrderDto(
    val itemId: String,
    val quantity: Double,
    val unit: String,
    val counterParty: String,
    val transaction: String,
    val deliveryDates: List<Instant>
) {
}

class OrderDto (
    val id: UUID?,
    val itemId: UUID?,
    val quantity: Double?,
    val unit: String?,
    val itemName: String?,
    val itemNameSl: String?,
    val sellerName: String?,
    val buyerName: String?,
    val deliveries: List<DeliveryForOrder>,
    val status: String?
)

class DeliveryForOrder (
    val deliveryDate: Instant
)