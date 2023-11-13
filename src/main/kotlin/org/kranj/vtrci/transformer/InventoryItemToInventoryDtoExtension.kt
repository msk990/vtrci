package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.InventoryDto
import org.kranj.vtrci.dtos.UpdateInventoryDto
import org.kranj.vtrci.model.InventoryItem

fun InventoryItem.toInventoryDto(): InventoryDto {
    return InventoryDto(
        id = this.id,
        itemId = this.product?.item?.id,
        quantity = this.quantity,
        unit = this.unit,
        itemName = this.product?.item?.itemName,
        itemNameSl = this.product?.item?.itemNameSl,
        producerName = this.product?.producer?.organizationName,
        producerLat = this.product?.producer?.lat,
        producerLong = this.product?.producer?.long,
        lotNumber = this.lotNumber,
        productionDate = this.productionDate,
        expirationDate = this.expirationDate,
        artSrc = this.product?.art?.src,
        artSimple = this.product?.art?.simple,
        //images = this.product?.images,
        owner = this.owner

    )
}

