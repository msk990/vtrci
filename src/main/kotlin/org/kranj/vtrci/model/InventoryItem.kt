package org.kranj.vtrci.model

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "inventory")
class InventoryItem(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @ManyToOne(cascade = [CascadeType.PERSIST])
    val item: Item?,

    @Column(name = "quantity")
    val quantity: Double?,

    @ManyToOne(cascade = [CascadeType.PERSIST])
    val product: Product?,

    @Column(name = "lot_number")
    val lotNumber: String,

    @Column(name = "production_date")
    val productionDate: Instant,

    @Column(name = "expiration_date")
    val expirationDate: Instant,

    @Column(name = "owner")
    val owner: String



) {
}