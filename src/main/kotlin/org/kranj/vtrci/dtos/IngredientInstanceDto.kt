package org.kranj.vtrci.dtos

import java.util.UUID

class IngredientInstanceDto(
    val instId: UUID,
    val eco: Boolean,
    val local: Boolean,
    val quality: Boolean,
    val schema: Boolean,
    val garden: Boolean,
    val productId: UUID?
) {
}