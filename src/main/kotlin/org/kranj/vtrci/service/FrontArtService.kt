package org.kranj.vtrci.service


import org.kranj.vtrci.dtos.NewFrontArtDto
import org.kranj.vtrci.model.Art
import org.kranj.vtrci.model.FrontArt
import org.kranj.vtrci.repository.FrontArtRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*
@Service
class FrontArtService (
    var repository: FrontArtRepository,
    private val fileStorageService: FileStorageService
        ){

    fun getAll(pageable: Pageable): Page<FrontArt> = repository.findAll(pageable)

    fun getById(id: UUID): FrontArt = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)


    fun create(art: NewFrontArtDto):FrontArt {
        val fileName = fileStorageService.storeFile(art.file)
        val newArt = FrontArt(
            UUID.randomUUID(),
            art.name,
            fileName
        )
        return repository.save(newArt)
    }

    fun remove(id:UUID) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

}