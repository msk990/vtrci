package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "ingredient")
class Ingredient (
        @Id @GeneratedValue(strategy = GenerationType.UUID)
        var id: UUID?,

        @ManyToOne(cascade=[CascadeType.DETACH])
        @PrimaryKeyJoinColumn
        @JsonIgnore
        @JsonProperty(value = "item")
        val item:Item?,

        @Column(name = "ingredient_quantity")
        val quantity:Double,

        @Column(name = "special")
        val special:Boolean? = false
        )
{
//        constructor() : this(
//                UUID.randomUUID(),
//                Item(),
//                0.0,
//                false
//        )
}