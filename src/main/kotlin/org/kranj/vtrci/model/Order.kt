package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "order_entity")
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
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

    @ManyToOne(cascade = [CascadeType.PERSIST])
    val proposer: Organization?,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var deliveries: List<Delivery>?,

    val status: String


){

}
