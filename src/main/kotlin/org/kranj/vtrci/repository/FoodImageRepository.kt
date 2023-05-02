package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.FoodImage
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FoodImageRepository: JpaRepository<FoodImage, UUID> {

  //  fun findByFoodId(foodId: UUID): List<FoodImage>
}