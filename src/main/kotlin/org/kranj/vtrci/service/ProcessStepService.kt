package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.NewImageDto
import org.kranj.vtrci.dtos.NewProcessStepDto
import org.kranj.vtrci.dtos.NewProductImageDto
import org.kranj.vtrci.model.FoodImage
import org.kranj.vtrci.model.ProcessStep
import org.kranj.vtrci.model.Product
import org.kranj.vtrci.model.ProductImage
import org.kranj.vtrci.repository.FoodImageRepository
import org.kranj.vtrci.repository.ProcessStepRepository
import org.kranj.vtrci.repository.ProductImageRepository
import org.kranj.vtrci.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class ProcessStepService(
    var repository: ProcessStepRepository,
    val productRepository: ProductRepository,
    private val fileStorageService: FileStorageService
) {


    fun getAll(): List<ProcessStep> = repository.findAll()
    fun getById(id: UUID): ProcessStep = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(stp: NewProcessStepDto): ResponseEntity<Any> {
        val product = productRepository.getReferenceById(stp.productId)
        val fileName = fileStorageService.storeFile(stp.file)
        val processStep = ProcessStep(
            UUID.randomUUID(),
            position = stp.position,
            name = fileName,
            description = ""
        )
        var newProcessSteps = product.steps?.filter { step -> step.position != stp.position }?.toMutableSet()

        newProcessSteps?.add(processStep)

        val newProduct = Product(
            product.id,
            product.externalId,
            product.item,
            product.producer,
            product.images,
            newProcessSteps,
            product.art,
            product.tag
        )
        return if (productRepository.existsById(product.id!!)) {
            product.id = product.id
            val savedProductStep = productRepository.save(newProduct).steps?.filter { it.position == stp.position }
            ResponseEntity.status(HttpStatus.OK).body(savedProductStep)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(productId: UUID, position: Int): ResponseEntity<Any> {
        val product = productRepository.getReferenceById(productId)
        var newProcessSteps = product.steps?.filter { step -> step.position != position }?.toMutableSet()

        val newProduct = Product(
            product.id,
            product.externalId,
            product.item,
            product.producer,
            product.images,
            newProcessSteps,
            product.art,
            product.tag
        )
        return if (productRepository.existsById(product.id!!)) {
            product.id = product.id
            val savedFoodImage = productRepository.save(newProduct).steps?.filter { it.position == position }
            ResponseEntity.status(HttpStatus.OK).body(savedFoodImage)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}