package org.kranj.vtrci.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="process_steps")
class ProcessStep (
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        var id: UUID?,

        @Column(name="position")
        val position: Int,

        @Column(name="name")
        val name: String,

        @Column(name="description")
        val description: String?,



        ) {
}