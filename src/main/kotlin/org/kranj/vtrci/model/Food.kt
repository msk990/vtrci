package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "food")
data class Food(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @Column(name = "food_name")
    var foodName: String,

    @Column(name = "food_name_sl")
    var foodNameSl: String,

    @Column(name = "gen")
    val gen: Boolean,

    @Column(name = "food_processing")
    val processing: String,

    @Column(name = "portionSize")
    val portionSize: Double,

    @Column(name = "energy_kj")
    val energyKj: Double,

    @Column(name = "energy_kcal")
    val energyKcal: Double,

    @Column(name = "protein")
    val protein: Double,

    @Column(name = "carbs")
    val carbs: Double,

    @Column(name = "sugars")
    val sugars: Double,

    @Column(name = "dietary_fibre")
    val dietaryFibre: Double,

    @Column(name = "fat")
    val fat: Double,

    @Column(name = "saturated")
    val saturated: Double,

    @Column(name = "ca")
    val ca: Double,

    @Column(name = "fe")
    val fe: Double,

    @Column(name = "mg")
    val mg: Double,

    @Column(name = "k")
    val k: Double,

    @Column(name = "na")
    val na: Double,

    @Column(name = "zn")
    val zn: Double,

    @Column(name = "carotenoide")
    val carotenoide: Double,

    @Column(name = "retinol")
    val retinol: Double,

    @Column(name = "thiamin")
    val thiamin: Double,

    @Column(name = "riboflavin")
    val riboflavin: Double,

    @Column(name = "niacin")
    val niacin: Double,

    @Column(name = "b6")
    val b6: Double,

    @Column(name = "b12")
    val b12: Double,

    @Column(name = "folate")
    val folate: Double,

    @Column(name = "vitamin_c")
    val vitaminC: Double,

    @Column(name = "vitamin_d")
    val vitaminD: Double,

    @Column(name = "vitamin_e")
    val vitaminE: Double,


    @OneToMany(cascade = [CascadeType.ALL])
    val ingredients: Set<Ingredient>?,


    @OneToMany (cascade = [CascadeType.ALL])
    val images: Set<FoodImage>?,


    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinTable(name="join_food_art",
        joinColumns=[JoinColumn(name="id_food", referencedColumnName="id", insertable = false, updatable = false)],
        inverseJoinColumns=[JoinColumn(name="id_art", referencedColumnName="id")])
    val art: Art?,

    @ManyToMany (cascade = [CascadeType.ALL])
    val stages: Set<FoodStage>?,

    @ManyToMany(cascade = [CascadeType.PERSIST])
    val tag: Set<MealTags>?,

    @JsonIgnore
    @ManyToMany()
    @JoinTable(name="join_meal_food",
    joinColumns=[JoinColumn(name="id_food", referencedColumnName="id")],
    inverseJoinColumns=[JoinColumn(name="id_meal", referencedColumnName="id")])
    val meals: List<Meal>?,



    @JsonIgnore
    @OneToMany()
    @JoinColumn(name = "food")
//    @JoinTable(name="join_instance_food",
//    joinColumns=[JoinColumn(name="id_food", referencedColumnName="id")],
//    inverseJoinColumns=[JoinColumn(name="id_instance", referencedColumnName="id")])
    val instances: List<FoodInstance>?,

    ) {
//
//    constructor(id: UUID, foodName: String, foodNameSl: String, gen: Any, processing:String) : this(
//    UUID.randomUUID(),
//    "",
//        "",
//        true,
//        "",
//   mutableSetOf(),
//
//    )
}