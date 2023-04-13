package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository

interface ItemsRepository : JpaRepository<Item, UUID> {
    fun findByItemNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Item?>

    fun findByItemNameContainingIgnoreCaseAndTagIdEquals(itemName: String, tagId: UUID?, pageable: Pageable): Page<Item>


   // @Query("SELECT i, t FROM Item i JOIN Tag t WHERE i.itemName like %:txt% AND (t.tag in :tags OR i.tag IS EMPTY)")
   @Query("SELECT i, t FROM Item i LEFT JOIN Tag t WHERE lower(i.itemName) like lower(concat('%', :txt,'%'))")
   // @Query("SELECT i, t FROM Item i LEFT JOIN Tag t WHERE t.tag like %:txt%")
    fun findAllContaining(txt:String,
                         // tags:List<String>?,
                          pageable: Pageable): Page<Item?>

//fun findByItemNameContainingAndTagsTagIn(txt:String, tags:List<String>?, pageable:Pageable):Page<Item?>
}