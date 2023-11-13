package org.kranj.vtrci.model

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "notifications")
class Notification(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @ManyToOne(fetch = FetchType.EAGER)
    val delivery: Delivery?,

    @ManyToOne(fetch = FetchType.EAGER)
    val inventory: InventoryItem?,

    val message: String,

    val status: String,

    @ManyToOne(fetch = FetchType.EAGER)
    val organization: Organization,

    val issued: Instant
        ){
}