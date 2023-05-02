package org.kranj.vtrci.model

import jakarta.persistence.Column
import jakarta.persistence.*

import java.util.*

@Entity
@Table(name = "foods_stages")
class FoodStage (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,


    @Column(name="position")
    val position: Int,
    @Column(name="name")
    val name: String,
) {
}