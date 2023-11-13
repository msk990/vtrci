package org.kranj.vtrci.repository

import org.kranj.vtrci.dtos.OrderDto
import org.kranj.vtrci.model.OrderEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderRepository : JpaRepository<OrderEntity, UUID> {

fun findByItem_ItemNameContainingIgnoreCaseAndSeller_IdEqualsOrBuyer_IdEquals(name: String?, orgId:UUID?, org2:UUID?, pageable: Pageable): Page<OrderEntity>

fun findByItem_ItemNameContainingIgnoreCaseAndSeller_IdIsOrBuyer_IdIs(name: String?, orgId:UUID?, org2:UUID?, pageable: Pageable): Page<OrderEntity>
}