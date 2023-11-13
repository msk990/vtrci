package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.NewOrganizationImageDto
import org.kranj.vtrci.dtos.NewProductImageDto
import org.kranj.vtrci.model.Organization
import org.kranj.vtrci.model.OrganizationImage
import org.kranj.vtrci.model.ProductImage
import org.kranj.vtrci.repository.OrganizationRepository
import org.kranj.vtrci.repository.OrganizationImageRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class OrganizationImageService(
    var repository: OrganizationImageRepository,
    val organizationRepository: OrganizationRepository,
    private val fileStorageService: FileStorageService,
    val userService: UserService
) {

    fun getAll(): List<ProductImage> = repository.findAll()
    fun getById(id: UUID): ProductImage = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(img: NewOrganizationImageDto): ResponseEntity<Any> {
        val organization = userService.getOrgFromUser()

        if (organization != null){
            val fileName = fileStorageService.storeFile(img.file)


            val orgImage = OrganizationImage(
                UUID.randomUUID(),
                position = img.position,
                name = fileName
            )

            var newOrgImages = organization?.images?.filter { image -> image.position != img.position }?.toMutableSet()
            newOrgImages?.add(orgImage)

            val newOrganization = Organization(
                organization.id,
                organization.externalId,
                organization.organizationName,
                organization.address,
                organization.country,
                organization.lat,
                organization.long,
                newOrgImages,
                organization.certificates,
                organization.products,
                organization.users
            )
            val savedFoodImage = organizationRepository.save(newOrganization).images?.filter { it.position == img.position }
           return ResponseEntity.status(HttpStatus.OK).body(savedFoodImage)
        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)


    }

    fun update(position: Int): ResponseEntity<Any>{
        val organization = userService.getOrgFromUser()

        if (organization != null){




            var newOrgImages = organization?.images?.filter { image -> image.position != position }?.toMutableSet()


            val newOrganization = Organization(
                organization.id,
                organization.externalId,
                organization.organizationName,
                organization.address,
                organization.country,
                organization.lat,
                organization.long,
                newOrgImages,
                organization.certificates,
                organization.products,
                organization.users
            )
            val savedFoodImage = organizationRepository.save(newOrganization).images?.filter { it.position == position }
            return ResponseEntity.status(HttpStatus.OK).body(savedFoodImage)
        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)


    }
}