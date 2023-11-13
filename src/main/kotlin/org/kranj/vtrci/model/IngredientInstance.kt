package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "ingredient_instance")
class IngredientInstance (
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @ManyToOne
    @JoinColumn(name = "ingredient")
    val ingredient: Ingredient,



    val eco: Boolean,
    val local: Boolean,
    val quality: Boolean,
    val schema: Boolean,
    val garden: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", insertable = false, updatable = false)

    val product:Product?
        ){
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    var foodInstance: FoodInstance? = null
}