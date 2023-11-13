package org.kranj.vtrci.dtos

import org.kranj.vtrci.model.Organization
import org.kranj.vtrci.model.ProductImage
import java.time.Instant
import java.util.UUID

data class InventoryDto (
    val id: UUID?,
    val itemId: UUID?,
    val quantity: Double?,
    val unit: String?,
    val itemName: String?,
    val itemNameSl: String?,
    val producerName: String?,
    val producerLat: Double?,
    val producerLong: Double?,
    val lotNumber: String,
    val productionDate: Instant,
    val expirationDate: Instant,
    val artSrc: String?,
    val artSimple: String?,
   // val images: Set<ProductImage>?,
    val owner: Organization?
        )

data class NewInventoryDto (
    val quantity: Double,
    val unit: String,
    val productId: UUID,
    val lotNumber: String,
    val productionDate: Long,
    val expirationDate: Long,
  //  val owner: String
        )

data class UpdateInventoryDto(
     val id: String,
    val quantity: Double
)