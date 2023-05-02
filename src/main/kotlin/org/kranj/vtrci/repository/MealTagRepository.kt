package org.kranj.vtrci.repository

import org.kranj.vtrci.model.MealTags
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MealTagRepository: JpaRepository<MealTags,UUID> {
}