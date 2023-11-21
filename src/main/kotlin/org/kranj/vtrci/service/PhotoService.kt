package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.NewFrontArtDto
import org.kranj.vtrci.model.FrontArt
import org.kranj.vtrci.model.Photo
import org.kranj.vtrci.repository.PhotoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class PhotoService(
    val repository : PhotoRepository,
    private val fileStorageService: FileStorageService
) {
    fun getAll(pageable: Pageable): Page<Photo> = repository.findAll(pageable)

    fun getById(id: UUID): Photo = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(art: NewFrontArtDto):Photo {
        val fileName = fileStorageService.storeFile(art.file)
        val newArt = Photo(
            UUID.randomUUID(),
            art.name,
            fileName,
            setOf()
        )
        return repository.save(newArt)
    }

    fun remove(id:UUID) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

}