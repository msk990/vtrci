package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
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

//    @ManyToOne(cascade = [CascadeType.PERSIST])
//    @JoinColumn(name = "item")
//    val item: Item?,

    @Column(name = "quantity")
    val quantity: Double?,

    @Column(name = "unit")
    val unit: String?,

    @ManyToOne(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    @JoinColumn(name = "product")
    val product: Product?,

    @Column(name = "lot_number")
    val lotNumber: String,

    @Column(name = "production_date")
    val productionDate: Instant,

    @Column(name = "expiration_date")
    val expirationDate: Instant,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    val owner: Organization?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custodian")
    val custodian: Organization?,

    val status: String?,

    @JsonIgnore
    @ManyToMany(mappedBy = "inventory",cascade = [CascadeType.ALL])
    val deliveries: List<Delivery>?




) {
}