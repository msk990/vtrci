package org.kranj.vtrci.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "survey")
class Survey (
    @Id

    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    val priority: String?,

    val solution: String?,

    val information: String?,

    val benefit: String?,

    val mostLiked: String?,

    val name: String?,

    val email: String?,

    val comment: String?
)