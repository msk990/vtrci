package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.FoodDto
import org.kranj.vtrci.model.Food
import org.kranj.vtrci.model.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Component

@Component
class FoodPageableTransformer: Transformer<Page<Food>, Page<FoodDto>> {
    override fun transform(source: Page<Food>): Page<FoodDto> {
        return PageImpl(
            source.content.map(Food::toFoodDto),
            source.pageable,
            source.totalElements
        )
    }

}