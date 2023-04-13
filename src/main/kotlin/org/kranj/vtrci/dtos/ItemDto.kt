package org.kranj.vtrci.dtos

import org.kranj.vtrci.model.Tag
import java.util.*

data class ItemDto (
    var id: UUID?,
    val processing: String?,
    val itemName: String?,
    val itemNameSl: String,
    val macroNutrients: MacroNutrients,
    val microNutrients: MicroNutrients,
    val tag: Set<Tag>?

)

data class NewItemDto (

    val processing: String?,
    val itemName: String?,
    val itemNameSl: String,
    val macroNutrients: MacroNutrients,
    val microNutrients: MicroNutrients,
    val tag: Set<Tag>?

)