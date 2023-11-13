package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.NewCertificateDto
import org.kranj.vtrci.model.Certificate
import org.kranj.vtrci.model.Organization
import org.kranj.vtrci.repository.CertificatesRepository
import org.kranj.vtrci.repository.OrganizationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class CertificatesService(
    val repository: CertificatesRepository,
    val organizatioRepository: OrganizationRepository,
    val userService: UserService,
    private val fileStorageService: FileStorageService
) {

    fun getById(id: UUID): Certificate = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getByOrganization(orgId: UUID) : Set<Certificate>? {
        return organizatioRepository.getReferenceById(orgId).certificates
    }

    fun getMine() : Set<Certificate>? {
        val organization = userService.getOrgFromUser()
        return organization?.certificates
    }

    fun create(cert: NewCertificateDto) : ResponseEntity<Any> {
        var organization = userService.getOrgFromUser()

        if (organization != null) {
            val fileName = fileStorageService.storeFile(cert.file)
            val certificate = Certificate(
                id = UUID.randomUUID(),
                certificateName = cert.certificateName,
                certificateImage = fileName
            )
            val newCertificate = repository.save(certificate)


            val newCertificates = organization.certificates?.toMutableSet()
            newCertificates?.add(newCertificate)

            val newOrganization = Organization(
                organization.id,
                organization.externalId,
                organization.organizationName,
                organization.address,
                organization.country,
                organization.lat,
                organization.long,
                organization.images,
                newCertificates,
                organization.products,
                organization.users
            )
            val savedOrg = organizatioRepository.save(newOrganization)
            return ResponseEntity.status(HttpStatus.OK).body(savedOrg)
        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun delete(id:String): ResponseEntity<Any>{
        val certId = UUID.fromString(id)
        var organization = userService.getOrgFromUser()
        if (organization != null) {

            val oldCertificate = repository.getReferenceById(certId)


            val newCertificates = organization.certificates?.filter{certificate -> certificate.id != oldCertificate.id}?.toMutableSet()


            val newOrganization = Organization(
                organization.id,
                organization.externalId,
                organization.organizationName,
                organization.address,
                organization.country,
                organization.lat,
                organization.long,
                organization.images,
                newCertificates,
                organization.products,
                organization.users
            )
            val savedOrg = organizatioRepository.save(newOrganization)
            return ResponseEntity.status(HttpStatus.OK).body(savedOrg)
        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

}