package org.kranj.vtrci.model

import jakarta.persistence.*
import java.util.*
@Entity
@Table(name="foods_images")
data class FoodImage (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @Column(name="position")
    val position: Int,
    @Column(name="name")
    val name: String,

        ) {
//    constructor(id: UUID?, position: Int, name: String) : this(
//        UUID.randomUUID(),
//        0,
//        ""
//    )
}