package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "photos")
class Photo (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @Column(name = "name")
    val name: String,

    @Column(name="file")
    val src: String,

    @JsonIgnore
    @OneToMany (cascade = [CascadeType.ALL], mappedBy = "photo")
    val foodPhotos: Set<FoodPhoto>?,
        ) {
}