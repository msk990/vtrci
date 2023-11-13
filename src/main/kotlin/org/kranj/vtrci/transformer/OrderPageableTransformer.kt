package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.ItemDto
import org.kranj.vtrci.dtos.OrderDto
import org.kranj.vtrci.model.Item
import org.kranj.vtrci.model.OrderEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Component

@Component
class OrderPageableTransformer: Transformer<Page<OrderEntity>, Page<OrderDto>> {
    override fun transform(source: Page<OrderEntity>): Page<OrderDto> {
        return PageImpl(
            source.content.map(OrderEntity::toOrderDto),
            source.pageable,
            source.totalElements
        )
    }
}