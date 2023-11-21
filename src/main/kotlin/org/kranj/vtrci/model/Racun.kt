package org.kranj.vtrci.model

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "racun")
class Racun (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val items: List<RacunItem>,

    val racunDate: Instant,

    @ManyToOne
    val racuner: Organization,

    @ManyToOne
    val racunee: Organization,

    val status: String = "ordered"

        ){
}