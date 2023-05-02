package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "producers")
class Producer(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @Column(name = "external_id")
    val externalId: String?,

    @Column(name = "producer_name")
    val producerName: String,

    @Column(name = "address")
    val address: String?,

    @Column(name = "country")
    val country: String?,

    @Column(name = "lat")
    val lat: Double?,

    @Column(name = "long")
    val long: Double?,

    @OneToMany (cascade = [CascadeType.ALL])
    val images: Set<FarmImage>?,

    @ManyToMany(cascade = [CascadeType.PERSIST])
    val certificates: Set<Certificate>?,

    @OneToMany(mappedBy ="producer", cascade = [CascadeType.ALL])
    @JsonIgnore
    val products: List<Product>?


) {
}