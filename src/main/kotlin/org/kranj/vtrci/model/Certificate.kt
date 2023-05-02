package org.kranj.vtrci.model

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "certificates")
class Certificate (
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        val id: UUID?,

    val certificateName: String,

    val countryName: String?,

    val certificateNumber: String?,
        ) {
}