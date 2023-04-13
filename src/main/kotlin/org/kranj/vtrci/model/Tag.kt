package org.kranj.vtrci.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "tags")
class Tag (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @Column(name = "tag")
    val tag: String,

    @Column(name = "tag_sl")
    val tagSl: String,

    @Column(name = "color")
    val color: String,

        )


{
    constructor() : this(
        UUID.randomUUID(),
        "",
        "",
        ""

    )


}