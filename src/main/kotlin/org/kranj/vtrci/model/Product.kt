package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "products")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @Column(name = "external_id")
    val externalId: String?,

    @ManyToOne(cascade = [CascadeType.PERSIST])
    val item: Item,

    @ManyToOne(fetch = FetchType.EAGER)

    val producer: Producer?,

    @OneToMany(cascade = [CascadeType.ALL])
    val images: Set<ProductImage>?,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="join_product_art",
        joinColumns=[JoinColumn(name="id_product", referencedColumnName="id", insertable = false, updatable = false)],
        inverseJoinColumns=[JoinColumn(name="id_art", referencedColumnName="id")])
    val art: Art?,

    @ManyToMany(cascade = [CascadeType.PERSIST])
    val tag: Set<Tag>?

    ) {
}