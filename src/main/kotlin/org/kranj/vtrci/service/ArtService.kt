package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.NewArtDto
import org.kranj.vtrci.dtos.NewFrontArtDto
import org.kranj.vtrci.model.Art
import org.kranj.vtrci.model.FrontArt
import org.kranj.vtrci.repository.ArtRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class ArtService (
    var repository: ArtRepository,
    private val fileStorageService: FileStorageService
        ){
    fun getAll(pageable: Pageable): Page<Art> = repository.findAll(pageable)

    fun getById(id: UUID): Art = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)


    fun create(art: NewArtDto):Art {
        val srcName = fileStorageService.storeFile(art.src)
        val simpleName = fileStorageService.storeFile(art.simple)
        val newArt = Art(
            UUID.randomUUID(),
            art.name,
            srcName,
            simpleName
        )
        return repository.save(newArt)
    }

    fun remove(id:UUID) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}