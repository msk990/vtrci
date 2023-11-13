package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "delivery")
data class Delivery (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,



    @ManyToOne(cascade = [CascadeType.PERSIST])
    val item: Item,

    @Column(name = "quantity")
    val quantity: Double?,

    @Column(name = "unit")
    val unit: String?,

    @ManyToOne(cascade = [CascadeType.PERSIST])
    val seller: Organization?,

    @ManyToOne(cascade = [CascadeType.PERSIST])
    val buyer: Organization?,

    @ManyToOne(cascade = [CascadeType.PERSIST])
    val deliverer: Organization?,

    val deliveryDate: Instant,

    @ManyToMany()
    @JoinTable(name="join_delivery_inventory",
        joinColumns=[JoinColumn(name="id_delivery", referencedColumnName="id")],
        inverseJoinColumns=[JoinColumn(name="id_inventory", referencedColumnName="id")])
    val inventory: List<InventoryItem>?,


    val status: String,
        ) {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    var order: OrderEntity? = null
}