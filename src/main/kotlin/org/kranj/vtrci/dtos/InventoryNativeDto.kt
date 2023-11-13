package org.kranj.vtrci.dtos

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant
import java.util.*

interface InventoryNativeDto{

    val id: UUID?
    val itemId: UUID?
    val quantity: Double?
    val unit: String?
    val itemName: String?
    val itemNameSl: String?
    val producerName: String?
    val producerLat: Double?
    val producerLong: Double?
    val lotNumber: String
    val productionDate: Instant
    val expirationDate: Instant
    val status: String

}