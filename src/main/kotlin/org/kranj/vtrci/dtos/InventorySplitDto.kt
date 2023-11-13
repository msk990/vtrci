package org.kranj.vtrci.dtos

import org.kranj.vtrci.model.InventoryItem

class InventorySplitDto(
    val newInventory: InventoryItem,
    val remainingInventory: InventoryItem?
) {
}