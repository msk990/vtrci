package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.NewProductDto
import org.kranj.vtrci.dtos.ProductDto
import org.kranj.vtrci.model.Product
import org.kranj.vtrci.repository.ArtRepository
import org.kranj.vtrci.repository.ItemsRepository
import org.kranj.vtrci.repository.OrganizationRepository
import org.kranj.vtrci.repository.ProductRepository
import org.kranj.vtrci.transformer.ProductPageableTransformer
import org.kranj.vtrci.transformer.toProductDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class ProductService(
    val productRepository: ProductRepository,
    val itemRepository:ItemsRepository,
    val producerRepository: OrganizationRepository,
    val artRepository: ArtRepository,
    val productPageableTransformer: ProductPageableTransformer,
    val userService: UserService
) {

    fun getAll(pageable: Pageable): Page<ProductDto> {
        return(
        productPageableTransformer.transform(productRepository.findAll(pageable))
        )
    }

    fun getAllByProducer(pageable: Pageable): Page<ProductDto> {
        val producer = userService.getOrgFromUser()
        val orgId = producer?.id
        return(
                productPageableTransformer.transform(productRepository.findByProducerIdEquals(orgId,pageable))
                )
    }

    fun getById(id: UUID): ProductDto = productRepository.findByIdOrNull(id)?.toProductDto() ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getByContent(name: String,  pageable: Pageable): Page<ProductDto>? {
        return productPageableTransformer.transform(productRepository.findByItem_ItemNameContainingIgnoreCase(name,pageable))

        //  return  itemPageableTransformer.transform(repository.findByItemNameContainingIgnoreCaseAndTagIdEquals(name, tag, pageable))
    }

    fun getByContentAndProducer(name: String, pageable:Pageable) : Page<ProductDto>? {
        val producer = userService.getOrgFromUser()
        val orgId = producer?.id
        return productPageableTransformer.transform(productRepository.findByItem_ItemNameContainingIgnoreCaseAndProducer_IdEquals(name,orgId,pageable))
    }

    fun getByProducer(producerId: String) : List<ProductDto>? {
        val prodId = UUID.fromString(producerId)
        return productRepository.findByProducerId(prodId).map(Product::toProductDto)
    }

    fun getByItemIdIn(idList: List<UUID>): List<ProductDto> {

//        val uuidList = idList.map { UUID.fromString(it) }
        return productRepository.findByItemIdIn(idList).map(Product::toProductDto)
    }

    fun getByItemId(id: UUID): List<ProductDto> {

//        val uuidList = idList.map { UUID.fromString(it) }
        return productRepository.findByItemId(id).map(Product::toProductDto)
    }
    fun create(pr:NewProductDto):Product {
        val externalId = pr.externalId
        val item = itemRepository.getReferenceById(pr.itemId)
        val producer = userService.getOrgFromUser()

        val newProduct = Product(
            UUID.randomUUID(),
            externalId,
            item,
            producer,
            setOf(),
            setOf(),
            null,
            setOf()
        )

        return productRepository.save(newProduct)

    }
    fun remove(id: UUID) {
        if (productRepository.existsById(id)) productRepository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun addArt(artId: UUID, productId: UUID): Product {
        val product = productRepository.getReferenceById(productId)
        val art = artRepository.getReferenceById(artId)

        val newProduct = Product(
            product.id,
            product.externalId,
            product.item,
            product.producer,
            product.images,
            product.steps,
            art,
            product.tag
        )
        return productRepository.save(newProduct)
    }

    fun removeArt(productId: UUID): Product {
        val product = productRepository.getReferenceById(productId)


        val newProduct = Product(
            product.id,
            product.externalId,
            product.item,
            product.producer,
            product.images,
            product.steps,
            null,
            product.tag
        )
        return productRepository.save(newProduct)
    }

}