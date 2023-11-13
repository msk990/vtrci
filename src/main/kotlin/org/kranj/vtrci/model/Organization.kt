package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "organizations")
class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @Column(name = "external_id")
    val externalId: String?,

    @Column(name = "organization_name")
    val organizationName: String,

    @Column(name = "address")
    val address: String?,

    @Column(name = "country")
    val country: String?,

    @Column(name = "lat")
    val lat: Double?,

    @Column(name = "long")
    val long: Double?,

    @OneToMany (cascade = [CascadeType.ALL])
    val images: Set<OrganizationImage>?,

    @ManyToMany(cascade = [CascadeType.PERSIST])
    val certificates: Set<Certificate>?,

    @OneToMany(mappedBy ="producer", cascade = [CascadeType.ALL])
    @JsonIgnore
    val products: List<Product>?,

    @OneToMany(mappedBy ="organization", cascade = [CascadeType.ALL])
    @JsonIgnore
    val users: List<UserEntity>?,


) {
}