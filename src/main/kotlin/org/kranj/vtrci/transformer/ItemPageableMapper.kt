package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.ItemDto
import org.kranj.vtrci.model.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

fun mapItemsPage(items: Page<Item>) : Page<ItemDto>? {
    if (items == null) {
        return null;
    }
    return items.map(Item::toItemDto);
}
