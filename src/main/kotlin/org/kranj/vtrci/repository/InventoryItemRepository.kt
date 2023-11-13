package org.kranj.vtrci.repository

import org.kranj.vtrci.model.InventoryItem
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface InventoryItemRepository : JpaRepository<InventoryItem,UUID> {

    //fun findByProduct_Item_Id(itemId:UUID): List<InventoryItem>

    fun findByProduct_Item_IdAndOwner_Id(itemId:UUID, orgId: UUID?): List<InventoryItem>

    fun findByProduct_Item_Id(itemId:UUID): List<InventoryItem>
    fun findByProduct_IdEqualsAndOwner_IdEquals(productId:UUID, orgId: UUID?): List<InventoryItem>

    fun findByProductId(productId:UUID): List<InventoryItem>
    fun findByOwnerEquals(owner:String): List<InventoryItem>

    fun findByOwner_IdOrCustodian_IdEquals(orgId:UUID?, org2:UUID?): List<InventoryItem>

}