package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "food_instance")
class FoodInstance(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
//    @JoinTable(name="join_instance_food",
//        joinColumns=[JoinColumn(name="id_instance", referencedColumnName="id")],
//        inverseJoinColumns=[JoinColumn(name="id_food", referencedColumnName="id")])
   @ManyToOne
    @JoinColumn(name = "food", )
    val food: Food?,


    @OneToMany(mappedBy = "foodInstance", cascade = [CascadeType.ALL])
    var ingredientInstances: List<IngredientInstance>?,

    @JsonIgnore
    @ManyToOne()
    @JoinTable(name="join_meal_food_instance",
        joinColumns=[JoinColumn(name="id_food_instance", referencedColumnName="id")],
        inverseJoinColumns=[JoinColumn(name="id_meal", referencedColumnName="id")])
    var meal: Meal?,

    ) {
}