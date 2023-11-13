package org.kranj.vtrci.repository

import org.kranj.vtrci.model.FoodInstance
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FoodInstanceRepository : JpaRepository<FoodInstance, UUID> {

fun findByMealId(mealId: UUID): List<FoodInstance>
}