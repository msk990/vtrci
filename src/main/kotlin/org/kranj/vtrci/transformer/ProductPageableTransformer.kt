package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.ProductDto
import org.kranj.vtrci.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Component

@Component
class ProductPageableTransformer:Transformer<Page<Product>, Page<ProductDto>> {
    override fun transform(source: Page<Product>): Page<ProductDto> {
        return PageImpl(
            source.content.map(Product::toProductDto),
            source.pageable,
            source.totalElements
        )
    }
}