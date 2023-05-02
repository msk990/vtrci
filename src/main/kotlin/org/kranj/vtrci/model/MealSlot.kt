package org.kranj.vtrci.model

import jakarta.persistence.*

@Entity
@Table(name = "meal_slot")
class MealSlot(
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    val id: Long,

    val position: Int,
    @ManyToOne
    val frontArt: FrontArt?

) {
}