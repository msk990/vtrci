package org.kranj.vtrci.dtos

import org.kranj.vtrci.model.Art
import org.kranj.vtrci.model.FoodImage
import org.kranj.vtrci.model.ProductImage
import java.util.*

data class ProductDto (
    var id: UUID?,
    val externalId: String?,
    val itemId: UUID?,
    val itemName: String?,
    val itemNameSl:String?,
    val producerId: UUID?,
    val producerName: String?,
    val producerAddress: String?,
    val images: MutableSet<ProductImage>?,
    val art: Art?,
    val lat: Double?,
    val long: Double?,

    )

data class NewProductDto (

    val externalId: String?,
    val itemId: UUID,
    val producerId: UUID,

    )