package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.NewImageDto
import org.kranj.vtrci.dtos.NewProductImageDto
import org.kranj.vtrci.model.FoodImage
import org.kranj.vtrci.model.Product
import org.kranj.vtrci.model.ProductImage
import org.kranj.vtrci.repository.FoodImageRepository
import org.kranj.vtrci.repository.ProductImageRepository
import org.kranj.vtrci.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class ProductImageService(
    var repository: ProductImageRepository,
    val productRepository: ProductRepository,
    private val fileStorageService: FileStorageService
) {


    fun getAll(): List<ProductImage> = repository.findAll()
    fun getById(id: UUID): ProductImage = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(img: NewProductImageDto): ResponseEntity<Any> {
        val product = productRepository.getReferenceById(img.productId)
        val fileName = fileStorageService.storeFile(img.file)
        val productImage = ProductImage(
            UUID.randomUUID(),
            position = img.position,
            name = fileName
        )
        var newProductImages = product.images?.filter { image -> image.position !== img.position }?.toMutableSet()
        newProductImages?.add(productImage)

        val newProduct = Product(
            product.id,
            product.externalId,
            product.item,
            product.producer,
            newProductImages,
            product.art,
            product.tag
        )
        return if (productRepository.existsById(product.id!!)) {
            product.id = product.id
            val savedFoodImage = productRepository.save(newProduct).images?.filter { it.position == img.position }
            ResponseEntity.status(HttpStatus.OK).body(savedFoodImage)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(productId: UUID, position: Int): ResponseEntity<Any> {
        val product = productRepository.getReferenceById(productId)
        var newProductImages = product.images?.filter { image -> image.position !== position }?.toMutableSet()

        val newProduct = Product(
            product.id,
            product.externalId,
            product.item,
            product.producer,
            newProductImages,
            product.art,
            product.tag
        )
        return if (productRepository.existsById(product.id!!)) {
            product.id = product.id
            val savedFoodImage = productRepository.save(newProduct).images?.filter { it.position == position }
            ResponseEntity.status(HttpStatus.OK).body(savedFoodImage)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}