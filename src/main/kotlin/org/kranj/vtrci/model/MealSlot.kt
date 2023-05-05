package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "meal_slot")
class MealSlot(
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    val id: Long,

    val position: Int,
    @ManyToOne
    val frontArt: FrontArt?,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinTable(name="join_slot_meal",
        joinColumns=[JoinColumn(name="id_slot", referencedColumnName="position")],
        inverseJoinColumns=[JoinColumn(name="id_meal", referencedColumnName="id")])
    val meals: Set<Meal>?

) {
}