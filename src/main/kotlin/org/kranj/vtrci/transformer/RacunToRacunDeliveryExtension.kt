package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.RacunDelivery
import org.kranj.vtrci.model.Racun

fun Racun.toRacunDelivery() : RacunDelivery {
    val color: String = when(this.status) {
        "ordered" -> "#FCCA46"
        "delivered" -> "#6CAE75"

        else -> "#FCCA46"
    }

    return RacunDelivery(
        racId = this.id,
        items = this.items,
        racunerName = this.racuner.organizationName,
        racuneeName = this.racunee.organizationName,
        racunDate = this.racunDate,
        status = this.status,
        start = this.racunDate,
        title = this.racunee.organizationName,
        allDay = true,
        color = color
    )

}