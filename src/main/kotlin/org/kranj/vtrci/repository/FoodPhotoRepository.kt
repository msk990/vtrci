package org.kranj.vtrci.repository

import org.kranj.vtrci.model.FoodPhoto
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FoodPhotoRepository : JpaRepository<FoodPhoto, UUID> {
}