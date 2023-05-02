package org.kranj.vtrci.repository

import org.kranj.vtrci.model.ProductImage
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductImageRepository : JpaRepository<ProductImage, UUID> {
}