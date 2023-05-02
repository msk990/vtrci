package org.kranj.vtrci.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="identities")
class Identity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @Column(name = "external_id")
    val externalId: String?,

    @Column(name = "id_name")
    val idName: String,

    @Column(name = "role")
    val role: String

    ) {
}