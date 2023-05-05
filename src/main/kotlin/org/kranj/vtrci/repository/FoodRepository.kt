package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FoodRepository : JpaRepository<Food, UUID> {

    fun findByFoodNameContainingIgnoreCaseAndTagIdEquals(foodName: String, tagId: UUID?, pageable: Pageable): Page<Food>

    fun findByFoodNameContainingIgnoreCaseOrFoodNameSlContainingIgnoreCase(foodName: String, content:String, pageable: Pageable): Page<Food>

    fun findByFoodNameContainingIgnoreCaseOrFoodNameSlContainingIgnoreCaseAndTagIdEquals(foodName: String, content:String, tagId: UUID?, pageable: Pageable): Page<Food>
}