package org.kranj.vtrci.repository

import org.kranj.vtrci.model.MealSlot
import org.springframework.data.jpa.repository.JpaRepository

interface MealSlotRepository : JpaRepository<MealSlot, Int> {
}