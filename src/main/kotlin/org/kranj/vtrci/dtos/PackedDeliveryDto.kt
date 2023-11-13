package org.kranj.vtrci.dtos

class InventoryForDelivery(
    val id: String,
    val quantity: Double
){

}
class PackedDeliveryDto(
    val deliveryId: String,
    val inventory: List<InventoryForDelivery>
) {
}