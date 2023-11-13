package org.kranj.vtrci.model

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "proposal")
class Proposal (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @ManyToOne(fetch = FetchType.LAZY)
    val item: Item,

    @Column(name = "quantity")
    val quantity: Double,

    @Column(name = "unit")
    val unit: String,

    @ManyToOne(fetch = FetchType.EAGER)
    val proposer: Organization,

    @ManyToOne(fetch = FetchType.EAGER)
    val proposed: Organization,

    @Column(name = "is_buy")
    val isBuy: Boolean,

    @Column(name = "delivery_dates")
    val deliveryDates: List<LocalDate>,

    val status: String,


    ) {

}