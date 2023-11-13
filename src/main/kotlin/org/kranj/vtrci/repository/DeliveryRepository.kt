package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Delivery
import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant
import java.util.*

interface DeliveryRepository : JpaRepository<Delivery, UUID>{

    fun findByBuyer_IdEqualsOrOrder_Seller_IdEquals(orgId:UUID?, org2:UUID?):List<Delivery>
    fun findByBuyer_IdEqualsOrOrder_Seller_IdEqualsAndDeliveryDateBetween(orgId:UUID?, org2:UUID?, start: Instant, end:Instant):List<Delivery>
}