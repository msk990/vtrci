package org.kranj.vtrci.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="front_art")
class FrontArt(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @Column(name = "name")
    val name: String,

    @Column(name="file")
    val src: String

) {
}