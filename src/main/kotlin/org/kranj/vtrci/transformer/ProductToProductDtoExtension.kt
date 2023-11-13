package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.ProductDto
import org.kranj.vtrci.model.Product

fun Product.toProductDto() : ProductDto {
    return ProductDto(
        id = this.id,
        externalId = this.externalId,
        itemId = this.item.id,
        itemName = this.item.itemName,
        itemNameSl = this.item.itemNameSl,
        producerId = this.producer?.id,
        producerName = this.producer?.organizationName,
        producerAddress = this.producer?.address,
        images = this.images?.toMutableSet(),
        steps = this.steps?.toMutableSet(),
        art = this.art,
        lat = this.producer?.lat,
        long = this.producer?.long
    )
}