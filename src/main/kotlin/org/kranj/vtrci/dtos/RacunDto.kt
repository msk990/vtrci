package org.kranj.vtrci.dtos

import org.kranj.vtrci.model.RacunItem
import java.time.Instant
import java.util.*

class RacunDto (
    val id: String?,
    val items: List<RacunItemDto>,
    val racunDate: Instant,
    val racunee: UUID

        ){

}

data class RacunItemDto (
    val productId: UUID,
    val name: String,
    val quantity: Double,
    val unit: String
    )

data class RacunDelivery(
    val racId: UUID?,
    val items: List<RacunItem>?,
    val racunerName: String?,
    val racuneeName: String?,
    val racunDate: Instant,
    val status: String,
    val start: Instant,
    val title: String,
    val allDay: Boolean,
    val color: String
        )




