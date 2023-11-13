package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="art")
class Art(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @Column(name = "name")
    val name: String,


    @Column(name="src")
    val src: String,

    @Column(name="simple")
    val simple: String,

    @JsonIgnore
    @OneToMany( cascade = [CascadeType.PERSIST])
    @JoinTable(name="join_product_art",
        joinColumns=[JoinColumn(name="id_art", referencedColumnName="id")],
        inverseJoinColumns=[JoinColumn(name="id_product", referencedColumnName="id")])
    val products: Set<Product>?,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinTable(name="join_food_art",
        joinColumns=[JoinColumn(name="id_art", referencedColumnName="id")],
        inverseJoinColumns=[JoinColumn(name="id_food", referencedColumnName="id")])
    val foods: Set<Food>?,

    @JsonIgnore
@OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
@JoinTable(name="join_item_art",
    joinColumns=[JoinColumn(name="id_art", referencedColumnName="id")],
    inverseJoinColumns=[JoinColumn(name="id_item", referencedColumnName="id")])
val items: Set<Item>?


) {
}