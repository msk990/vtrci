package org.kranj.vtrci.service

import org.kranj.vtrci.model.Identity
import org.kranj.vtrci.repository.IdentityRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class IdentityService (val repository: IdentityRepository) {
    fun getAll():List<Identity> = repository.findAll()

    fun getById(id: UUID): Identity = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(ident: Identity): Identity = repository.save(ident)

    fun remove(id: UUID) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id: UUID, ident: Identity): Identity {
        return if (repository.existsById(id)) {
            ident.id = id
            repository.save(ident)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }


}