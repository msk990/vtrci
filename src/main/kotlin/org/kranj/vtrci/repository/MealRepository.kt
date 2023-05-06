package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Meal
import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

interface MealRepository : JpaRepository<Meal, UUID> {
    fun findByStart(start: LocalDateTime) : Meal

    fun findByStartGreaterThanAndStartLessThan(start: Instant, end: Instant) : List<Meal>
}