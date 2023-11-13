package org.kranj.vtrci.repository

import org.kranj.vtrci.dtos.InventoryNativeDto
import org.kranj.vtrci.model.InventoryItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface InventoryNativeRepository: JpaRepository<InventoryItem, UUID> {

    @Query(value = """select i.id, i.expiration_date as expirationDate, i.status, i.lot_number as lotNumber, i.production_date as productionDate, i.quantity , i.unit ,
        i2.id as itemId, i2.item_name_en as itemName, i2.item_name_sl as itemNameSl,
        o.organization_name as producerName, o.lat as producerLat, o.long as producerLong
                from inventory i
                left join products p on i.product = p.id
                left join items i2 on p.item_id = i2.id
                left join organizations o on p.producer_id = o.id
                where i.product = :productId
                and i.owner = :orgId
                """, nativeQuery = true)
    fun findByProductAndOwner(@Param("productId") productId: UUID, @Param("orgId") orgId:UUID): List<InventoryNativeDto?>?

    @Query(value = """select i.id, i.expiration_date as expirationDate, i.status, i.lot_number as lotNumber, i.production_date as productionDate, i.quantity , i.unit ,
        i2.id as itemId, i2.item_name_en as itemName, i2.item_name_sl as itemNameSl,
        o.organization_name as producerName, o.lat as producerLat, o.long as producerLong
                from inventory i
                left join products p on i.product = p.id
                left join items i2 on p.item_id = i2.id
                left join organizations o on p.producer_id = o.id
                where i2.id = :itemId
                and i.owner = :orgId
                """, nativeQuery = true)
    fun findByItemAndOwner(@Param("itemId") itemId: UUID, @Param("orgId") orgId:UUID): List<InventoryNativeDto?>?

    @Query(value = """select i.id, i.expiration_date as expirationDate, i.status, i.lot_number as lotNumber, i.production_date as productionDate, i.quantity , i.unit ,
        i2.id as itemId, i2.item_name_en as itemName, i2.item_name_sl as itemNameSl,
        o.organization_name as producerName, o.lat as producerLat, o.long as producerLong
                from inventory i
                left join products p on i.product = p.id
                left join items i2 on p.item_id = i2.id
                left join organizations o on p.producer_id = o.id
                where i2.id = :itemId
                and i.owner = :orgId
                and i.status = 'available'
                
                """, nativeQuery = true)
    fun findUnpackedByItemAndOwner(@Param("itemId") itemId: UUID, @Param("orgId") orgId:UUID): List<InventoryNativeDto?>?


}