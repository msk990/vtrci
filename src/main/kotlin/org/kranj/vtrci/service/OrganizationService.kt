package org.kranj.vtrci.service
import org.kranj.vtrci.model.Organization
import org.kranj.vtrci.repository.OrganizationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
class OrganizationService(

    val repository: OrganizationRepository,
    val userService: UserService
    ) {
    fun getAll():List<Organization> = repository.findAll()

    fun getById(id: String): Organization = repository.findByIdOrNull(UUID.fromString(id)) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getMy():Organization {
        val organization = userService.getOrgFromUser()
        if (organization != null){
            return organization
        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
    fun create(producer: Organization): Organization = repository.save(producer)

    fun remove(id: UUID) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id:UUID, producer: Organization): Organization {
        return if (repository.existsById(id)) {
            producer.id = id
            repository.save(producer)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}