package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.ItemDto
import org.kranj.vtrci.model.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Component

@Component
class ItemPageableTransformer:Transformer<Page<Item>, Page<ItemDto>> {
    override fun transform(source: Page<Item>): Page<ItemDto> {
        return PageImpl(
             source.content.map(Item::toItemDto),
            source.pageable,
            source.totalElements
        )
    }
}