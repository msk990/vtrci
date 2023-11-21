package org.kranj.vtrci.controller



import org.kranj.vtrci.dtos.NewFrontArtDto
import org.kranj.vtrci.model.FrontArt
import org.kranj.vtrci.model.Photo
import org.kranj.vtrci.service.FrontArtService
import org.kranj.vtrci.service.PhotoService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RequestMapping("api/v1/photos")
@RestController
class PhotoController (val service: PhotoService) {

    @CrossOrigin
    @GetMapping
    fun getAllArt(pageable: Pageable) = service.getAll(pageable)


    @CrossOrigin
    @GetMapping("/{id}")
    fun getArtById(@PathVariable id: UUID): Photo = service.getById(id)

    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteArt(@PathVariable id: UUID) = service.remove(id)

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createNewArt(
        @RequestParam("name") name: String,
        @RequestParam("file") multipartFile: MultipartFile,
    ) = service.create(NewFrontArtDto(name, multipartFile) )
}