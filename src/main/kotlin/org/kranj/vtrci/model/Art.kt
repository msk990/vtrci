package org.kranj.vtrci.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="art")
class Art(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @Column(name = "name")
    val name: String,

    @Column(name="src")
    val src: String,

    @Column(name="simple")
    val simple: String

) {
}