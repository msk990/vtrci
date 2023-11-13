package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Ingredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface IngredientsRepository : JpaRepository<Ingredient, UUID> {

    fun existsByItemId(itemId: UUID?): Boolean
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = """delete from ingredient where item_id = :itemId""", nativeQuery = true)
    fun deleteByItemId(@Param("itemId") itemId: UUID)
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = """delete from food_ingredients where ingredients_id = :ingId""", nativeQuery = true)
    fun deleteFromIngredientRelationship(@Param("ingId") ingId: UUID)

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = """delete from food_ingredients where ingredients_id in (select id from ingredient i where i.item_id = :itemId)""", nativeQuery = true)
    fun deleteFromIngredientRelationshipItem(@Param("itemId") itemId: UUID)

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = """delete from food_ingredients where food_id = :foodId""", nativeQuery = true)
    fun deleteFromFoodRelationship(@Param("foodId") foodId: UUID)
}