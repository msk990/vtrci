package org.kranj.vtrci.repository

import org.kranj.vtrci.model.InventoryItem
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface InventoryItemRepository : JpaRepository<InventoryItem,UUID> {

    fun findByItem(itemId:UUID): List<InventoryItem>
    fun findByOwnerEquals(owner:String): List<InventoryItem>

}