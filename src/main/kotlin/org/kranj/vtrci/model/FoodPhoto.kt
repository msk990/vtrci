package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "foodz_photos")
class FoodPhoto (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id" )
    val photo: Photo,

    val position: Int
        ){
}