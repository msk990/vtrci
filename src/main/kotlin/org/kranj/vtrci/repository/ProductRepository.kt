package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Item
import org.kranj.vtrci.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductRepository : JpaRepository<Product, UUID> {
    fun findByItem_ItemNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Product>

    fun findByTagIdEquals(tag:UUID?, pageable: Pageable): Page<Product>
    fun findByItem_ItemNameContainingIgnoreCaseAndTagIdEquals(itemName: String, tagId: UUID?, pageable: Pageable): Page<Product>



}