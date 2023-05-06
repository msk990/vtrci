package org.kranj.vtrci.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import jakarta.persistence.*
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "meals")
class Meal (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,
//
//    @JsonSerialize(using = LocalDateTimeSerializer::class.java)
//    @JsonDeserialize(using = LocalDatetimeDeserializer.class::class.java)
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")

    val start: Instant,

    @ManyToMany()
    @JoinTable(name="join_meal_food",
        joinColumns=[JoinColumn(name="id_meal", referencedColumnName="id", insertable = false, updatable = false)],
        inverseJoinColumns=[JoinColumn(name="id_food", referencedColumnName="id")])
    val foods: List<Food>?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="join_slot_meal",
        joinColumns=[JoinColumn(name="id_meal", referencedColumnName="id", insertable = false, updatable = false)],
        inverseJoinColumns=[JoinColumn(name="id_slot", referencedColumnName="position")])
    val mealSlot: MealSlot
)
{
}