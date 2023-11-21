package org.kranj.vtrci.model

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.util.*

@Entity
@Table(name = "racun_item")
class RacunItem(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @ManyToOne
    @JoinColumn(name = "product", )
    @OnDelete(action = OnDeleteAction.CASCADE)
    val product: Product?,

    val quantity: Double,

    val unit: String,




) {
}