package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.util.*


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
        val special:Boolean? = false,

        @JsonIgnore
   //     @OneToMany(mappedBy="ingredient", orphanRemoval = true, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
      @OneToMany
        @JoinColumn(name = "ingredient")
        val ingInstances: List<IngredientInstance>?
)
{
//        constructor() : this(
//                UUID.randomUUID(),
//                Item(),
//                0.0,
//                false
//        )
}