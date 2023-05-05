package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.InventoryDto
import org.kranj.vtrci.model.InventoryItem

fun InventoryItem.toInventoryDto(): InventoryDto {
    return InventoryDto(
        id = this.id,
        itemId = this.item?.id,
        quantity = this.quantity,
        itemName = this.item?.itemName,
        itemNameSl = this.item?.itemNameSl,
        producerName = this.product?.producer?.producerName,
        producerLat = this.product?.producer?.lat,
        producerLong = this.product?.producer?.long,
        lotNumber = this.lotNumber,
        productionDate = this.productionDate,
        expirationDate = this.expirationDate,
        artSrc = this.product?.art?.src,
        artSimple = this.product?.art?.simple,
        images = this.product?.images,
        owner = this.owner

    )
}