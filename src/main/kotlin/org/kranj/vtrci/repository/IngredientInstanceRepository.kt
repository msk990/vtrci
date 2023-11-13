package org.kranj.vtrci.repository

import org.kranj.vtrci.model.IngredientInstance
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IngredientInstanceRepository : JpaRepository<IngredientInstance, UUID> {
}